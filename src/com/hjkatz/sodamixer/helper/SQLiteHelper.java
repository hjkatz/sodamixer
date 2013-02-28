package com.hjkatz.sodamixer.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.hjkatz.sodamixer.database.*;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SQLiteHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "sodamixer";
    private static final int DATABASE_VERSION = 2;
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
}
