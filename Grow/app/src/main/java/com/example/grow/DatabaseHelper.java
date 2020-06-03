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
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "usersGrow";
    public static final int DATABASE_VERSION = 2;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedUserGrow.FeedEntry.TABLE_NAME + " (" +
                    FeedUserGrow.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedUserGrow.FeedEntry.COLUMN_NAME + " TEXT," +
                    FeedUserGrow.FeedEntry.COLUMN_PASS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedUserGrow.FeedEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_ENTRIES);
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
        db.execSQL(SQL_DELETE_ENTRIES);
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
        contentValues.put(FeedUserGrow.FeedEntry.COLUMN_NAME, s);
        contentValues.put(FeedUserGrow.FeedEntry.COLUMN_PASS, s1);
        db.replace(FeedUserGrow.FeedEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
//        Cursor res = db.rawQuery("select (id ||' : '||name || ' : ' ||password || ' : '|| datetime) as name from " + CONTACTS_TABLE_NAME, null);
        Cursor res = db.rawQuery("select (id ||' : '||name || ' : ' ||password) as groupCheck from " + FeedUserGrow.FeedEntry.TABLE_NAME, null);
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
        db.execSQL("UPDATE " + FeedUserGrow.FeedEntry.TABLE_NAME + " SET name = " + "'" + s + "', " + "password = " + "'" + s1 + "'");
        return true;
    }

    public boolean delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + FeedUserGrow.FeedEntry.TABLE_NAME);
        return true;
    }

    public boolean checkExist(String s) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = { s };
        String[] projection = {
                FeedUserGrow.FeedEntry._ID
        };

        Cursor cursor = db.query(
                FeedUserGrow.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                "name = ?",              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedUserGrow.FeedEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();
        if (itemIds.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean login(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] selectionArgs = {user};
        String[] projection = {
                FeedUserGrow.FeedEntry.COLUMN_PASS
        };

        Cursor cursor = db.query(
                FeedUserGrow.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                "name = ?",              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String passwordC = cursor.getString(cursor.getColumnIndex(FeedUserGrow.FeedEntry.COLUMN_PASS));
            itemIds.add(passwordC);
        }
        cursor.close();
        if (itemIds.contains(md5(pass))) {
            return true;
        }
        else {
            return false;
        }
    }
}
