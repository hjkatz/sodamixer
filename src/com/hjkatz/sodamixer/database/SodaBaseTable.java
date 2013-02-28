package com.hjkatz.sodamixer.database;

import android.database.sqlite.SQLiteDatabase;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaBaseTable
{

    public static final String TABLE = "tb_soda_base";
    public static final String PK = "soda_base";
    public static final String NAME = "name";
    private static final String create_table = "CREATE TABLE" +
            " ( " +
            NAME + " TEXT NOT NULL " +
            " PRIMARY KEY ( " + PK + " ) " +
            " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );
    }

    public static void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE );
        onCreate( db );
    }
}
