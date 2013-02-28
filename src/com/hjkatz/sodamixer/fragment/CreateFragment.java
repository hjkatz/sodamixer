package com.hjkatz.sodamixer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hjkatz.sodamixer.R;

/** Created By: Harrison Katz on Date: 2/26/13 */
public class CreateFragment extends Fragment
{
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        return inflater.inflate( R.layout.create_fragment, container, false );
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    public void addSoda( View v )
    {
    }
}