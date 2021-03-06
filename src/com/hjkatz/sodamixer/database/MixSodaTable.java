package com.hjkatz.sodamixer.database;

import android.database.sqlite.SQLiteDatabase;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class MixSodaTable
{

    public static final  String TABLE        = "tb_mix_soda";
    public static final  String PK           = "mix_soda";
    public static final  String MIX          = "mix";
    public static final  String BASE         = "base";
    public static final  String FLAVOR       = "flavor";
    private static final String create_table = "CREATE TABLE " + TABLE +
                                               " ( " +
                                               PK + " INTEGER , " +
                                               MIX + " INTEGER NOT NULL , " +
                                               BASE + " INTEGER NOT NULL , " +
                                               FLAVOR + " INTEGER NOT NULL , " +
                                               " FOREIGN KEY ( " + MIX + " ) REFERENCES " + MixTable.TABLE + " ( "
                                               + MixTable.PK + " ) , " +
                                               " FOREIGN KEY ( " + BASE + " ) REFERENCES " + SodaBaseTable.TABLE + " ( "
                                               + SodaBaseTable.PK + " ) , " +
                                               " FOREIGN KEY ( " + FLAVOR + " ) REFERENCES " + SodaFlavorTable.TABLE
                                               + " ( " + SodaFlavorTable.PK + " ) " +
                                               " PRIMARY KEY ( " + PK + " ) " +
                                               " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );
    }

    public static void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
    }
}
