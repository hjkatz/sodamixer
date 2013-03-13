package com.hjkatz.sodamixer.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.model.SodaFlavor;
import java.util.ArrayList;

/** Created By: harrison on Date: 3/2/13 */
public class SodaFlavorAdapter extends ArrayAdapter implements SpinnerAdapter
{

    private ArrayList<SodaFlavor> sodaFlavors;
    private Context               context;

    public SodaFlavorAdapter( Context context, int textViewResourceId, ArrayList<SodaFlavor> objects )
    {
        super( context, textViewResourceId );
        this.context = context;
        sodaFlavors = objects;
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

        viewHolder.textView.setText( sodaFlavors.get( position ).getNameFormatted() );

        return convertView;
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

        viewHolder.textView.setText( sodaFlavors.get( position ).getNameFormatted() );

        return convertView;
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

    public ArrayList<SodaFlavor> getSodaFlavors()
    {
        return sodaFlavors;
    }

    public void setSodaFlavors( ArrayList<SodaFlavor> sodaFlavors )
    {
        this.sodaFlavors = sodaFlavors;
    }

    private class ViewHolder
    {
        protected TextView textView;

    }
}
