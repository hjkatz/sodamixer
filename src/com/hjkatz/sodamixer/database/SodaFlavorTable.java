package com.hjkatz.sodamixer.database;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Map;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaFlavorTable
{

    public static final  String TABLE          = "tb_soda_flavor";
    public static final  String PK             = "soda_flavor";
    public static final  String NAME           = "name";
    public static final  String NAME_FORMATTED = "name_formatted";
    private static final String create_table   = "CREATE TABLE " + TABLE +
                                                 " ( " +
                                                 PK + " INTEGER , " +
                                                 NAME + " TEXT NOT NULL , " +
                                                 NAME_FORMATTED + " TEXT NOT NULL , " +
                                                 " PRIMARY KEY ( " + PK + " ) " +
                                                 " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );

        Map<String, String> flavorNames;

        flavorNames = new HashMap<String, String>();
        flavorNames.put( "regular", "Regular" );
        flavorNames.put( "cherry", "Cherry" );
        flavorNames.put( "cherry_vanilla", "Cherry Vanilla" );
        flavorNames.put( "fruit_punch", "Fruit Punch" );
        flavorNames.put( "grape", "Grape" );
        flavorNames.put( "lemon", "Lemon" );
        flavorNames.put( "lime", "Lime" );
        flavorNames.put( "orange", "Orange" );
        flavorNames.put( "orange_vanilla", "Orange Vanilla" );
        flavorNames.put( "peach", "Peach" );
        flavorNames.put( "raspberry", "Raspberry" );
        flavorNames.put( "strawberry", "Strawberry" );
        flavorNames.put( "vanilla", "Vanilla" );

        // Inserting initial data
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper( db, TABLE );
        db.beginTransaction();

        try
        {
            int name = ih.getColumnIndex( NAME );
            int name_formatted = ih.getColumnIndex( NAME_FORMATTED );

            for ( String s : flavorNames.keySet() )
            {
                ih.prepareForReplace();
                ih.bind( name, s );
                ih.bind( name_formatted, flavorNames.get( s ) );
                ih.execute();
            }
            db.setTransactionSuccessful();
        }
        finally
        {
            ih.close();
            db.endTransaction();
        }
    }

    public static void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
    }
}
