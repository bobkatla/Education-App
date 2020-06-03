package com.example.grow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TodoTasks";
    public static final int DATABASE_VERSION = 2;

    private static final String SQL_CREATE_ENTRIES_TASKS =
            "CREATE TABLE " + FeedTasks.FeedEntry.TABLE_NAME + " (" +
                    FeedTasks.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedTasks.FeedEntry.COLUMN_DATE + " TEXT," +
                    FeedTasks.FeedEntry.COLUMN_TIME + " TEXT," +
                    FeedTasks.FeedEntry.COLUMN_TASK + " TEXT)";

    private static final String SQL_DELETE_ENTRIES_TASKS =
            "DROP TABLE IF EXISTS " + FeedTasks.FeedEntry.TABLE_NAME;

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_ENTRIES_TASKS);
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
        db.execSQL(SQL_DELETE_ENTRIES_TASKS);
        onCreate(db);
    }

    public ArrayList<Data> getAllTasks(String chosenDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Data> array_list = new ArrayList<Data>();

        String[] projection = {
                FeedTasks.FeedEntry.COLUMN_TIME,
                FeedTasks.FeedEntry.COLUMN_TASK
        };

        String sortOrder =
                FeedTasks.FeedEntry.COLUMN_TIME + " ASC";

        String[] selectionArgs = {chosenDate};

        Cursor res = db.query(
                FeedTasks.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                "date = ?",              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder            // The sort order
        );
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            if ((res != null) && (res.getCount() > 0))
                array_list.add(new Data (
                        R.drawable.ic_1,
                        res.getString(res.getColumnIndex("time")),
                        res.getString(res.getColumnIndex("task"))));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean addTask(String date, String time, String task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedTasks.FeedEntry.COLUMN_DATE, date);
        contentValues.put(FeedTasks.FeedEntry.COLUMN_TIME, time);
        contentValues.put(FeedTasks.FeedEntry.COLUMN_TASK, task);
        db.replace(FeedTasks.FeedEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public void deleteTask(String time) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Define 'where' part of query.
        String selection = FeedTasks.FeedEntry.COLUMN_TIME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { time};
        // Issue SQL statement.
        int deletedRows = db.delete(FeedTasks.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void updateTask() {

    }
}
