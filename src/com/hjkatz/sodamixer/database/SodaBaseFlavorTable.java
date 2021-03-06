package com.hjkatz.sodamixer.database;

import android.database.sqlite.SQLiteDatabase;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaBaseFlavorTable
{

    public static final String TABLE = "tb_soda_base_flavor";
    public static final String PK = "soda_base_flavor";
    public static final String BASE = "soda_base";
    public static final String FLAVOR = "soda_flavor";
    private static final String create_table = "CREATE TABLE " + TABLE +
            " ( " +
            PK + " INTEGER , " +
            BASE + " INTEGER NOT NULL , " +
            FLAVOR + " INTEGER NOT NULL , " +
            " FOREIGN KEY ( " + BASE + " ) REFERENCES " + SodaBaseTable.TABLE + " ( " + SodaBaseTable.PK + " ) , " +
            " FOREIGN KEY ( " + FLAVOR + " ) REFERENCES " + SodaFlavorTable.TABLE + " ( " + SodaFlavorTable.PK + " ) " +
            " PRIMARY KEY ( " + PK + " ) " +
            " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );
    }

    public static void onUpgrade( SQLiteDatabase db,
                                  int oldVersion,
                                  int newVersion )
    {
    }
}
