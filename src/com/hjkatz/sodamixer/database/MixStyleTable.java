package com.hjkatz.sodamixer.database;

import android.database.sqlite.SQLiteDatabase;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class MixStyleTable
{

    public static final String TABLE = "tb_mix_soda";
    public static final String PK = "mix_soda";
    public static final String MIX = "mix";
    public static final String STYLE = "style";
    private static final String create_table = "CREATE TABLE" +
            " ( " +
            MIX + " INTEGER NOT NULL " +
            STYLE + " INTEGER NOT NULL " +
            " FOREIGN KEY ( " + MIX + " ) REFERENCES " + MixTable.TABLE + " ( " + MixTable.PK + " ) " +
            " FOREIGN KEY ( " + STYLE + " ) REFERENCES " + StyleTable.TABLE + " ( " + StyleTable.PK + " ) " +
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
