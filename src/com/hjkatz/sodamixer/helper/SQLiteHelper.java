package com.hjkatz.sodamixer.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.hjkatz.sodamixer.database.*;
import com.hjkatz.sodamixer.model.*;

import java.util.ArrayList;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SQLiteHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "sodamixer";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteHelper dbHelper;

    private SQLiteHelper( Context context )
    {
        super( context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION );
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
        MixStyleTable.onCreate( db );
        MixSodaTable.onCreate( db );
        SodaBaseFlavorTable.onCreate( db );

        setupBaseFlavorTable( db );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion,
                           int newVersion )
    {
        db.execSQL( "PRAGMA foreign_keys=ON;" );

        StyleTable.onUpgrade( db,
                oldVersion,
                newVersion );
        SodaBaseTable.onUpgrade( db,
                oldVersion,
                newVersion );
        SodaFlavorTable.onUpgrade( db,
                oldVersion,
                newVersion );
        MixTable.onUpgrade( db,
                oldVersion,
                newVersion );
        MixStyleTable.onUpgrade( db,
                oldVersion,
                newVersion );
        MixSodaTable.onUpgrade( db,
                oldVersion,
                newVersion );
        SodaBaseFlavorTable.onUpgrade( db,
                oldVersion,
                newVersion );
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

        return getAllSodaBases( db );
    }

    public ArrayList<SodaBase> getAllSodaBases( SQLiteDatabase db )
    {
        ArrayList<SodaBase> bases = new ArrayList<SodaBase>();

        try
        {
            Cursor cursor =
                    db.rawQuery( "SELECT * FROM " + SodaBaseTable.TABLE + " ORDER BY " + SodaBaseTable.NAME + " ASC ",
                            null );

            if ( cursor.moveToFirst() )
            {
                do
                {
                    SodaBase sodaBase = new SodaBase();
                    sodaBase.setId( cursor.getInt( cursor.getColumnIndex( SodaBaseTable.PK ) ) );
                    sodaBase.setName( cursor.getString( cursor.getColumnIndex( SodaBaseTable.NAME ) ) );
                    sodaBase.setNameFormatted( cursor.getString( cursor.getColumnIndex( SodaBaseTable.NAME_FORMATTED ) ) );
                    sodaBase.setIcon( cursor.getString( cursor.getColumnIndex( SodaBaseTable.ICON ) ) );
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

    // REMEMBER TO UPDATE BELOW METHOD TOO
    public ArrayList<SodaFlavor> getAllSodaFlavors()
    {
        ArrayList<SodaFlavor> flavors = new ArrayList<SodaFlavor>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return getAllSodaFlavors( db );
    }

    public ArrayList<SodaFlavor> getAllSodaFlavors( SQLiteDatabase db )
    {
        ArrayList<SodaFlavor> flavors = new ArrayList<SodaFlavor>();

        try
        {
            Cursor cursor = db.rawQuery( "SELECT * FROM " +
                    SodaFlavorTable.TABLE +
                    " ORDER BY " +
                    SodaFlavorTable.NAME_FORMATTED +
                    " ASC ",
                    null );

            if ( cursor.moveToFirst() )
            {
                do
                {
                    SodaFlavor sodaFlavor = new SodaFlavor();
                    sodaFlavor.setId( cursor.getInt( cursor.getColumnIndex( SodaFlavorTable.PK ) ) );
                    sodaFlavor.setName( cursor.getString( cursor.getColumnIndex( SodaFlavorTable.NAME ) ) );
                    sodaFlavor.setNameFormatted( cursor.getString( cursor.getColumnIndex( SodaFlavorTable.NAME_FORMATTED ) ) );
                    flavors.add( sodaFlavor );
                } while ( cursor.moveToNext() );
            }
        } catch ( SQLiteException e )
        {
            e.printStackTrace();
        }

        return flavors;
    }

    public ArrayList<SodaFlavor> getFlavorsByBase( SodaBase base )
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<SodaFlavor> flavors = new ArrayList<SodaFlavor>();

        try
        {
            Cursor cursor = db.rawQuery( "SELECT * " +
                    " FROM " +
                    SodaBaseFlavorTable.TABLE +
                    " JOIN " +
                    SodaFlavorTable.TABLE +
                    " ON " +
                    SodaBaseFlavorTable.TABLE +
                    "." +
                    SodaBaseFlavorTable.FLAVOR +
                    " = " +
                    SodaFlavorTable.TABLE +
                    "." +
                    SodaFlavorTable.PK +
                    " WHERE " +
                    SodaBaseFlavorTable.BASE +
                    " = " +
                    base.getId() +
                    " ORDER BY " +
                    SodaFlavorTable.NAME_FORMATTED +
                    " ASC ",
                    null );

            if ( cursor.moveToFirst() )
            {
                do
                {
                    SodaFlavor sodaFlavor = new SodaFlavor();
                    sodaFlavor.setId( cursor.getInt( cursor.getColumnIndex( SodaFlavorTable.PK ) ) );
                    sodaFlavor.setName( cursor.getString( cursor.getColumnIndex( SodaFlavorTable.NAME ) ) );
                    sodaFlavor.setNameFormatted( cursor.getString( cursor.getColumnIndex( SodaFlavorTable.NAME_FORMATTED ) ) );
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

    // TB_BASE_FLAVOR FUNCTIONS

    private void setupBaseFlavorTable( SQLiteDatabase db )
    {

        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper( db,
                SodaBaseFlavorTable.TABLE );
        db.beginTransaction();

        try
        {
            int base = ih.getColumnIndex( SodaBaseFlavorTable.BASE );
            int flavor = ih.getColumnIndex( SodaBaseFlavorTable.FLAVOR );

            ArrayList<SodaBase> bases = getAllSodaBases( db );
            ArrayList<SodaFlavor> flavors = getAllSodaFlavors( db );

            for ( SodaBase mBase : bases )
            {
                for ( SodaFlavor mFlavor : flavors )
                {
                    if ( mBase.getName().equals( "coke" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "vanilla" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "coke_diet" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "coke_zero" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "vanilla" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) ||
                                mFlavor.getName().equals( "lemon" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "coke_caffeine_free" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "vanilla" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "sprite" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "peach" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "sprite_zero" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "peach" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "fanta" ) )
                    {
                        if ( mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "peach" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "fanta_zero" ) )
                    {
                        if ( mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "peach" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "minute_maid_lemonade" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "minute_maid_light" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "powerade" ) )
                    {
                        if ( mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "lemon" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "powerade_zero" ) )
                    {
                        if ( mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) ||
                                mFlavor.getName().equals( "lime" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "lemon" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "cherry" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "hic" ) )
                    {
                        if ( mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "raspberry" ) ||
                                mFlavor.getName().equals( "strawberry" ) ||
                                mFlavor.getName().equals( "fruit_punch" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "raspberry_lime" ) ||
                                mFlavor.getName().equals( "orange_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "mellow_yellow" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "grape" ) ||
                                mFlavor.getName().equals( "orange" ) ||
                                mFlavor.getName().equals( "peach" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "pibb_xtra" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "pibb_zero" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "dr_pepper" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "dr_pepper_diet" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) ||
                                mFlavor.getName().equals( "cherry" ) ||
                                mFlavor.getName().equals( "cherry_vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "barqs" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) || mFlavor.getName().equals( "vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                    if ( mBase.getName().equals( "barqs_diet" ) )
                    {
                        if ( mFlavor.getName().equals( "regular" ) || mFlavor.getName().equals( "vanilla" ) )
                        {
                            ih.prepareForReplace();
                            ih.bind( base,
                                    mBase.getId() );
                            ih.bind( flavor,
                                    mFlavor.getId() );
                            ih.execute();
                        }
                    }
                }
            }
            db.setTransactionSuccessful();
        } finally
        {
            ih.close();
            db.endTransaction();
        }
    }

    // END TB_BASE_FLAVOR FUNCTIONS

    // TB_STYLE FUNCTIONS

    public ArrayList<Style> getAllStyles( SQLiteDatabase db )
    {
        ArrayList<Style> styles = new ArrayList<Style>();

        try
        {
            Cursor cursor = db.rawQuery( "SELECT * FROM " +
                    StyleTable.TABLE +
                    " ORDER BY " +
                    StyleTable.NAME_FORMATTED +
                    " ASC ",
                    null );

            if ( cursor.moveToFirst() )
            {
                do
                {
                    Style style = new Style();
                    style.setId( cursor.getInt( cursor.getColumnIndex( StyleTable.PK ) ) );
                    style.setNameFormatted( cursor.getString( cursor.getColumnIndex( StyleTable.NAME_FORMATTED ) ) );
                    styles.add( style );
                } while ( cursor.moveToNext() );
            }
        } catch ( SQLiteException e )
        {
            e.printStackTrace();
        }

        return styles;
    }

    // END TB_STYLE FUNCTIONS
}
