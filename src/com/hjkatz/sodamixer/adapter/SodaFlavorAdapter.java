package com.hjkatz.sodamixer.adapter;

import android.content.*;
import android.database.*;
import android.view.*;
import android.widget.*;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.model.*;

import java.util.*;

/** Created By: harrison on Date: 3/2/13 */
public class SodaFlavorAdapter implements SpinnerAdapter
{

    private ArrayList<SodaFlavor> sodaFlavors;
    private Context context;

    public SodaFlavorAdapter( Context context, int textViewResourceId, List objects )
    {
        this.context = context;
        sodaFlavors = (ArrayList<SodaFlavor>) objects;
    }

    @Override
    public View getDropDownView( int position, View convertView, ViewGroup viewGroup )
    {
        ViewHolder viewHolder;

        if ( convertView == null )
        {
            convertView = View.inflate( context, R.layout.soda_flavor_spinner_view, null );
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById( R.id.text );
            convertView.setTag( viewHolder );
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText( sodaFlavors.get( position ).getName() );

        return convertView;
    }

    private class ViewHolder
    {
        protected TextView textView;
    }

    @Override
    public Object getItem( int position )
    {
        return sodaFlavors.get( position );
    }

    @Override
    public long getItemId( int i )
    {
        return 0;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;

        if ( convertView == null )
        {
            convertView = View.inflate( context, R.layout.soda_flavor_spinner_view, null );
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById( R.id.text );
            convertView.setTag( viewHolder );
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText( sodaFlavors.get( position ).getName() );

        return convertView;
    }

    @Override
    public int getItemViewType( int i )
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public void registerDataSetObserver( DataSetObserver dataSetObserver )
    {
    }

    @Override
    public void unregisterDataSetObserver( DataSetObserver dataSetObserver )
    {
    }

    @Override
    public int getCount()
    {
        return sodaFlavors.size();
    }
}
