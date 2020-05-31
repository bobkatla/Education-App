package com.example.grow;

import android.provider.BaseColumns;

public final class FeedUserGrow {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedUserGrow() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "UserDetails";
        public static final String COLUMN_NAME_TITLE = "username";
        public static final String COLUMN_NAME_SUBTITLE = "password";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
