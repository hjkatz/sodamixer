package com.hjkatz.sodamixer.database;

import android.database.sqlite.SQLiteDatabase;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaTable
{

    public static final String TABLE = "tb_soda";
    public static final String PK = "soda";
    public static final String NAME = "name";
    public static final String BASE = "base";
    public static final String FLAVOR = "flavor";
    private static final String create_table = "CREATE TABLE " + TABLE +
            " ( " +
            PK + " INTEGER , " +
            NAME + " TEXT NOT NULL , " +
            BASE + " INTEGER NOT NULL , " +
            FLAVOR + " INTEGER NOT NULL , " +
            " PRIMARY KEY ( " + PK + " ) , " +
            " FOREIGN KEY ( " + BASE + " ) REFERENCES " + SodaBaseTable.TABLE + " ( " + SodaBaseTable.PK + " ) , " +
            " FOREIGN KEY ( " + FLAVOR + " ) REFERENCES " + SodaFlavorTable.TABLE + " ( " + SodaFlavorTable.PK + " ) " +
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
