package com.hjkatz.sodamixer.database;

import android.database.*;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class StyleTable
{

    public static final String TABLE = "tb_style";
    public static final String PK = "style";
    public static final String NAME_FORMATTED = "name_formatted";
    private static final String create_table = "CREATE TABLE " + TABLE +
            " ( " +
            PK + " INTEGER , " +
            NAME_FORMATTED + " TEXT NOT NULL , " +
            " PRIMARY KEY ( " + PK + " ) " +
            " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );

        String[] names =
                { "Dark", "Light", "Energy", "Sweet", "Bitter", "Sour", "Fruity", "Carbonated", "Non-Carbonated", "Healthy", "Tangy" };

        // Inserting initial data
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper( db,
                TABLE );
        db.beginTransaction();

        try
        {
            int name_formatted = ih.getColumnIndex( NAME_FORMATTED );

            for ( String s : names )
            {
                ih.prepareForReplace();
                ih.bind( name_formatted, s );
                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally
        {
            ih.close();
            db.endTransaction();
        }
    }

    public static void onUpgrade( SQLiteDatabase db,
                                  int oldVersion,
                                  int newVersion )
    {
    }
}
