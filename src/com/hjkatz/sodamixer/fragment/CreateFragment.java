package com.hjkatz.sodamixer.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.adapter.SodaBaseAdapter;
import com.hjkatz.sodamixer.adapter.SodaFlavorAdapter;
import com.hjkatz.sodamixer.helper.SQLiteHelper;
import com.hjkatz.sodamixer.model.SodaBase;
import com.hjkatz.sodamixer.model.SodaFlavor;
import com.hjkatz.sodamixer.model.Style;
import java.util.ArrayList;

/** Created By: Harrison Katz on Date: 2/26/13 */
public class CreateFragment extends Fragment
{

    public static TableLayout      sodaTable;
    private       int              sodaRows;
    private       SQLiteHelper     dbHelper;
    private       ArrayList<Style> selectedStyles;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        sodaRows = 0;
        dbHelper = SQLiteHelper.getDbHelper( getActivity() );
        selectedStyles = new ArrayList<Style>();

        return inflater.inflate( R.layout.create_fragment, container, false );
    }

    @Override
    public void onResume()
    {
        super.onResume();

        sodaTable = (TableLayout) getView().findViewById( R.id.sodaTable );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    public void addSodaRow( View v )
    {
        if ( sodaRows < 5 )
        {
            final View newView = View.inflate( getActivity(), R.layout.soda_table_row, null );
            sodaTable.addView( newView );
            sodaRows++;
            addSodaDialog( ( (ViewGroup) sodaTable.getChildAt( sodaRows ) ).getChildAt( 0 ) );
        }
        else
        {
            Toast.makeText( getActivity(), "Only 5 Sodas allowed in a mix!", Toast.LENGTH_SHORT ).show();
        }
    }

    public void deleteSodaRow( View v )
    {
        sodaTable.removeView( (TableRow) v.getParent() );
        sodaRows--;
    }

    public void addSodaDialog( final View v )
    {
        final View sodaDialogView = View.inflate( getActivity(), R.layout.soda_dialog, null );

        ArrayList<SodaBase> bases = dbHelper.getAllSodaBases();
        ArrayList<SodaFlavor> flavors = dbHelper.getAllSodaFlavors();

        final Gallery sodaBaseGallery = ( (Gallery) sodaDialogView.findViewById( R.id.sodaBaseGallery ) );
        final Spinner flavorSpinner = ( (Spinner) sodaDialogView.findViewById( R.id.flavorSpinner ) );

        SodaFlavorAdapter sfa = new SodaFlavorAdapter( getActivity(), R.id.flavorSpinner, flavors );
        flavorSpinner.setAdapter( sfa );
        sodaBaseGallery.setAdapter( new SodaBaseAdapter( getActivity(), R.id.sodaBaseGallery, bases ) );
        sodaBaseGallery.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected( AdapterView<?> adapterView, View view, int i, long l )
            {
                ArrayList<SodaFlavor> flavors = dbHelper.getFlavorsByBase( (SodaBase) adapterView.getSelectedItem() );
                SodaFlavorAdapter sfa = new SodaFlavorAdapter( getActivity(), R.id.flavorSpinner, flavors );
                flavorSpinner.setAdapter( sfa );
                flavorSpinner.invalidate();
            }

            @Override
            public void onNothingSelected( AdapterView<?> adapterView )
            {
            }
        } );

        new AlertDialog.Builder( getActivity() ).setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialogInterface, int i )
            {
                String base = ( (SodaBase) sodaBaseGallery.getSelectedItem() ).getNameFormatted();
                String flavor = ( (SodaFlavor) flavorSpinner.getSelectedItem() ).getNameFormatted();
                Button rowButton = (Button) ( (TableRow) v.getParent() ).getChildAt( 0 );
                rowButton.setText( flavor + " " + base );
            }
        } ).setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialogInterface, int i )
            {
                dialogInterface.dismiss();
            }
        } ).setView( sodaDialogView ).setCancelable( false ).create().show();
    }

    public void createMix( View v )
    {
//        Mix mix = new Mix();
//        Style style = new Style();
//        style.setName( (String) ( (Spinner) getView().findViewById( R.id.styleSpinner ) ).getSelectedItem() );
//        ArrayList<Style> styles = new ArrayList<Style>();
//        styles.add( style );
//        ArrayList<SodaBase> bases = new ArrayList<SodaBase>();
//        for ( int i = 0; i < sodaRows; i++ )
//        {
//            SodaBase base = new SodaBase();
//        }
    }

    public void selectStyles( final View v )
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        final ArrayList<Style> styles = dbHelper.getAllStyles( db );
        CharSequence[] names = new CharSequence[styles.size()];
        boolean[] selected = new boolean[styles.size()];

        for ( int i = 0; i < styles.size(); i++ )
        {
            names[i] = styles.get( i ).getNameFormatted();
            for ( Style s : selectedStyles )
            {
                if ( s.getNameFormatted().equalsIgnoreCase( (String) names[i] ) )
                {
                    selected[i] = true;
                }
            }
        }

        new AlertDialog.Builder( getActivity() ).setTitle( "Select Styles" ).setMultiChoiceItems( names, selected, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick( DialogInterface dialogInterface, int which, boolean checked )
            {
                if ( checked )
                {
                    selectedStyles.add( styles.get( which ) );
                }
                else
                {
                    selectedStyles.remove( styles.get( which ) );
                }
            }
        } ).setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialogInterface, int which )
            {
                String styleList = "";

                for ( Style s : selectedStyles )
                {
                    styleList = styleList.concat( s.getNameFormatted() + ", " );
                }

                if ( styleList.length() >= 2 )
                {
                    styleList = styleList.substring( 0, styleList.length() - 2 );
                }
                else
                {
                    styleList = "";
                }

                ( (EditText) v ).setText( styleList );
            }
        } ).setCancelable( false ).create().show();
    }

    public void clearUI( View v )
    {
    }
}