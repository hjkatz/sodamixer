package com.hjkatz.sodamixer.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.helper.SQLiteHelper;

/** Created By: Harrison Katz on Date: 2/26/13 */
public class MixesFragment extends ListFragment
{

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {

        SQLiteHelper dbHelper = SQLiteHelper.getDbHelper( this.getActivity() );

        return inflater.inflate( R.layout.mixes_fragment, container, false );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}