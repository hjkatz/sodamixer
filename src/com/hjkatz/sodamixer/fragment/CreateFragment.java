package com.hjkatz.sodamixer.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.adapter.*;
import com.hjkatz.sodamixer.helper.*;
import com.hjkatz.sodamixer.model.*;

import java.util.*;

/** Created By: Harrison Katz on Date: 2/26/13 */
public class CreateFragment extends Fragment
{

    public static TableLayout sodaTable;
    private int sodaRows;
    private SQLiteHelper dbHelper;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        sodaRows = 0;
        dbHelper = SQLiteHelper.getDbHelper( getActivity() );

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
            View newView = View.inflate( getActivity(), R.layout.soda_table_row, null );
            sodaTable.addView( newView );
            sodaRows++;
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

    public void addSodaDialog( View v )
    {
        final int viewId = v.getId();

        final View sodaDialogView = View.inflate( getActivity(), R.layout.soda_dialog, null );

        ArrayList<SodaBase> bases = dbHelper.getAllSodaBases();
        ArrayList<SodaFlavor> flavors = dbHelper.getAllSodaFlavors();

        final Gallery sodaBaseGallery = ( (Gallery) sodaDialogView.findViewById( R.id.sodaBaseGallery ) );
        final Spinner flavorSpinner = ( (Spinner) sodaDialogView.findViewById( R.id.flavorSpinner ) );

        sodaBaseGallery.setAdapter( new SodaBaseAdapter( getActivity(), R.id.sodaBaseGallery, bases ) );
        flavorSpinner.setAdapter( new SodaFlavorAdapter( getActivity(), R.id.flavorSpinner, flavors ) );

        new AlertDialog.Builder( getActivity() ).setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener()
        {
            // TODO CHANGES WRONG BUTTON TEXT
            @Override
            public void onClick( DialogInterface dialogInterface, int i )
            {
                String base = ( (SodaBase) sodaBaseGallery.getSelectedItem() ).getName();
                String flavor = ( (SodaFlavor) flavorSpinner.getSelectedItem() ).getName();
                TableRow row = (TableRow) sodaTable.findViewById( viewId ).getParent();
                Button rowButton = (Button) row.getChildAt( 0 );
                rowButton.setText( base + " " + flavor );
            }
        } ).setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialogInterface, int i )
            {
                dialogInterface.dismiss();
            }
        } ).setView( sodaDialogView ).create().show();
    }
}