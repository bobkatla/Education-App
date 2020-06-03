package com.example.grow;

import android.provider.BaseColumns;

public class FeedTasks {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedTasks() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String _ID = "id";
        public static final String TABLE_NAME = "Schedule";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TASK = "task";

    }
}
