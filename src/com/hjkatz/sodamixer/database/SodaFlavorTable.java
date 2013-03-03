package com.hjkatz.sodamixer.database;

import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import com.hjkatz.sodamixer.model.*;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaFlavorTable
{

    public static final String TABLE = "tb_soda_flavor";
    public static final String PK = "soda_flavor";
    public static final String NAME = "name";
    private static final String create_table = "CREATE TABLE " + TABLE +
            " ( " +
            PK + " INTEGER , " +
            NAME + " TEXT NOT NULL , " +
            " PRIMARY KEY ( " + PK + " ) " +
            " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );

        // Inserting initial data
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper( db, TABLE );
        db.beginTransaction();

        try
        {
            int name = ih.getColumnIndex( NAME );

            for ( String s : SodaFlavor.flavorNames.keySet() )
            {
                ih.prepareForReplace();
                ih.bind( name, s );
                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally
        {
            ih.close();
            db.endTransaction();
        }
    }

    public static void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE );
        onCreate( db );
    }
}
