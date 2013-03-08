package com.hjkatz.sodamixer.database;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Map;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaBaseTable
{

    public static final  String TABLE          = "tb_soda_base";
    public static final  String PK             = "soda_base";
    public static final  String NAME           = "name";
    public static final  String NAME_FORMATTED = "name_formatted";
    public static final  String ICON           = "icon";
    private static final String create_table   = "CREATE TABLE " + TABLE +
                                                 " ( " +
                                                 PK + " INTEGER , " +
                                                 NAME + " TEXT NOT NULL , " +
                                                 NAME_FORMATTED + " TEXT NOT NULL , " +
                                                 ICON + " TEXT NOT NULL , " +
                                                 " PRIMARY KEY ( " + PK + " ) " +
                                                 " ) ";

    public static void onCreate( SQLiteDatabase db )
    {
        db.execSQL( create_table );

        Map<String, String> baseNames = new HashMap<String, String>();
        baseNames.put( "barqs", "Barq's Root Beer" );
        baseNames.put( "barqs_diet", "Barq's Diet Root Beer" );
        baseNames.put( "coke", "Coca-Cola" );
        baseNames.put( "coke_caffeine_free", "Caffeine Free Diet Coca-Cola" );
        baseNames.put( "coke_diet", "Diet Coca-Cola" );
        baseNames.put( "coke_zero", "Coca-Cola Zero" );
        baseNames.put( "dr_pepper", "Dr. Pepper" );
        baseNames.put( "dr_pepper_diet", "Diet Dr. Pepper" );
        baseNames.put( "fanta", "Fanta" );
        baseNames.put( "fanta_zero", "Fanta Zero" );
        baseNames.put( "hic", "Hi-C" );
        baseNames.put( "mellow_yellow", "Mellow Yellow" );
        baseNames.put( "minute_maid_lemonade", "Minute Maid Lemonade" );
        baseNames.put( "minute_maid_light", "Minute Maid Light" );
        baseNames.put( "pibb_xtra", "Pibb Xtra" );
        baseNames.put( "pibb_zero", "Pibb Zero" );
        baseNames.put( "powerade", "Powerade" );
        baseNames.put( "powerade_zero", "Powerade Zero" );
        baseNames.put( "sprite", "Sprite" );
        baseNames.put( "sprite_zero", "Sprite Zero" );

        Map<String, String> icons;
        icons = new HashMap<String, String>();
        icons.put( "barqs", "ic_barqs" );
        icons.put( "barqs_diet", "ic_barqs_diet" );
        icons.put( "coke", "ic_coke" );
        icons.put( "coke_caffeine_free", "ic_coke_caffeine_free" );
        icons.put( "coke_diet", "ic_coke_diet" );
        icons.put( "coke_zero", "ic_coke_zero" );
        icons.put( "dr_pepper", "ic_dr_pepper" );
        icons.put( "dr_pepper_diet", "ic_dr_pepper_diet" );
        icons.put( "fanta", "ic_fanta" );
        icons.put( "fanta_zero", "ic_fanta_zero" );
        icons.put( "hic", "ic_hic" );
        icons.put( "mellow_yellow", "ic_mellow_yellow" );
        icons.put( "minute_maid_lemonade", "ic_minute_maid_lemonade" );
        icons.put( "minute_maid_light", "ic_minute_maid_light" );
        icons.put( "pibb_xtra", "ic_pibb_xtra" );
        icons.put( "pibb_zero", "ic_pibb_zero" );
        icons.put( "powerade", "ic_powerade" );
        icons.put( "powerade_zero", "ic_powerade_zero" );
        icons.put( "sprite", "ic_sprite" );
        icons.put( "sprite_zero", "ic_sprite_zero" );

        // Inserting initial data
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper( db, TABLE );
        db.beginTransaction();

        try
        {
            int name = ih.getColumnIndex( NAME );
            int name_formatted = ih.getColumnIndex( NAME_FORMATTED );
            int icon = ih.getColumnIndex( ICON );

            for ( String s : baseNames.keySet() )
            {
                ih.prepareForReplace();
                ih.bind( name, s );
                ih.bind( name_formatted, baseNames.get( s ) );
                ih.bind( icon, icons.get( s ) );
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
