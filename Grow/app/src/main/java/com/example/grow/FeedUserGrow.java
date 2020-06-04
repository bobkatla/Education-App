package com.example.grow;

import android.provider.BaseColumns;

public final class FeedUserGrow {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedUserGrow() {}

    // Inner class that defines the table contents
    public static class FeedEntry implements BaseColumns {
        public static final String _ID = "id";
        public static final String TABLE_NAME = "UserDetails";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PASS = "password";
    }
}
