package com.hjkatz.sodamixer.helper;

import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import com.hjkatz.sodamixer.database.*;
import com.hjkatz.sodamixer.model.*;

import java.util.*;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SQLiteHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "sodamixer";
    private static final int DATABASE_VERSION = 4;
    private static SQLiteHelper dbHelper;

    private SQLiteHelper( Context context )
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public static synchronized SQLiteHelper getDbHelper( Context context )
    {
        if ( dbHelper == null )
        {
            dbHelper = new SQLiteHelper( context );
        }
        return dbHelper;
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( "PRAGMA foreign_keys=ON;" );

        StyleTable.onCreate( db );
        SodaBaseTable.onCreate( db );
        SodaFlavorTable.onCreate( db );
        MixTable.onCreate( db );
        SodaTable.onCreate( db );
        MixStyleTable.onCreate( db );
        MixSodaTable.onCreate( db );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL( "PRAGMA foreign_keys=ON;" );

        StyleTable.onUpgrade( db, oldVersion, newVersion );
        SodaBaseTable.onUpgrade( db, oldVersion, newVersion );
        SodaFlavorTable.onUpgrade( db, oldVersion, newVersion );
        MixTable.onUpgrade( db, oldVersion, newVersion );
        SodaTable.onUpgrade( db, oldVersion, newVersion );
        MixStyleTable.onUpgrade( db, oldVersion, newVersion );
        MixSodaTable.onUpgrade( db, oldVersion, newVersion );
    }

    @Override
    public void onOpen( SQLiteDatabase db )
    {
        super.onOpen( db );

        db.enableWriteAheadLogging();
        db.execSQL( "PRAGMA foreign_keys=ON;" );
    }

    // TB_SODA_BASE FUNCTIONS

    public ArrayList<SodaBase> getAllSodaBases()
    {
        ArrayList<SodaBase> bases = new ArrayList<SodaBase>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            Cursor cursor = db.rawQuery( "SELECT * FROM " + SodaBaseTable.TABLE + " ORDER BY " + SodaBaseTable.NAME + " ASC ", null );

            if ( cursor.moveToFirst() )
            {
                do
                {
                    SodaBase sodaBase = new SodaBase();
                    sodaBase.setName( cursor.getString( cursor.getColumnIndex( SodaBaseTable.NAME ) ) );
                    bases.add( sodaBase );
                } while ( cursor.moveToNext() );
            }
        } catch ( SQLiteException e )
        {
            e.printStackTrace();
        }

        return bases;
    }

    // END TB_SODA_BASE FUNCTIONS

    // TB_SODA_FLAVOR FUNCTIONS

    public ArrayList<SodaFlavor> getAllSodaFlavors()
    {
        ArrayList<SodaFlavor> flavors = new ArrayList<SodaFlavor>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try
        {
            Cursor cursor = db.rawQuery( "SELECT * FROM " + SodaFlavorTable.TABLE + " ORDER BY " + SodaFlavorTable.NAME + " ASC ", null );

            if ( cursor.moveToFirst() )
            {
                do
                {
                    SodaFlavor sodaFlavor = new SodaFlavor();
                    sodaFlavor.setName( cursor.getString( cursor.getColumnIndex( SodaFlavorTable.NAME ) ) );
                    flavors.add( sodaFlavor );
                } while ( cursor.moveToNext() );
            }
        } catch ( SQLiteException e )
        {
            e.printStackTrace();
        }

        return flavors;
    }

    // END TB_SODA_FLAVOR FUNCTIONS
}
