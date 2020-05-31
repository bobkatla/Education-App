package com.example.grow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "usersGrow";
    public static final String CONTACTS_TABLE_NAME = "UserDetails";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASS = "password";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(
//                    "create table " + CONTACTS_TABLE_NAME + "(id INTEGER PRIMARY KEY, name text,password text,datetime default current_timestamp)"
            );
        } catch (SQLiteException e) {
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(db);
    }
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean insert(String s, String s1) {
        SQLiteDatabase db = this.getWritableDatabase();
        s1 = md5(s1);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", s);
        contentValues.put("password", s1);
        db.replace(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
//        Cursor res = db.rawQuery("select (id ||' : '||name || ' : ' ||password || ' : '|| datetime) as name from " + CONTACTS_TABLE_NAME, null);
        Cursor res = db.rawQuery("select (id ||' : '||name || ' : ' ||password) as groupCheck from " + CONTACTS_TABLE_NAME, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if ((res != null) && (res.getCount() > 0))
                array_list.add(res.getString(res.getColumnIndex("groupCheck")));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean update(String s, String s1) {
        SQLiteDatabase db = this.getWritableDatabase();
        s1 = md5(s1);
        db.execSQL("UPDATE " + CONTACTS_TABLE_NAME + " SET name = " + "'" + s + "', " + "password = " + "'" + s1 + "'");
        return true;
    }

    public boolean delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + CONTACTS_TABLE_NAME);
        return true;
    }

    public boolean checkExist(String s) {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> array_list = new ArrayList<String>();

        String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";

//        Cursor res = db.rawQuery("select (id ||' : '||name || ' : ' ||password) as groupCheck from " + CONTACTS_TABLE_NAME + " where (name)", null);
        Cursor cursor = db.query(
                CONTACTS_TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                name,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if ((res != null) && (res.getCount() > 0))
                array_list.add(res.getString(res.getColumnIndex("groupCheck")));
            res.moveToNext();
        }
        if (array_list.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}
