package com.hjkatz.sodamixer.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import com.hjkatz.sodamixer.R;

/** Created By: Harrison Katz on Date: 2/26/13 */
public class CreateFragment extends Fragment
{

    public static TableLayout sodaTable;
    private int sodaRows;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        sodaRows = 0;

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
        View sodaDialogView = View.inflate( getActivity(), R.layout.soda_dialog, null );

        new AlertDialog.Builder( getActivity() ).setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick( DialogInterface dialogInterface, int i )
            {
                // TODO ADD SODA TO LIST
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