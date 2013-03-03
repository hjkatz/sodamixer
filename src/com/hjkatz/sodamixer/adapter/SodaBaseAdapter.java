package com.hjkatz.sodamixer.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import com.hjkatz.sodamixer.*;
import com.hjkatz.sodamixer.model.*;

import java.util.*;

/** Created By: harrison on Date: 3/2/13 */
public class SodaBaseAdapter extends ArrayAdapter
{

    private ArrayList<SodaBase> sodaBases;
    private Context context;

    public SodaBaseAdapter( Context context, int textViewResourceId, List objects )
    {
        super( context, textViewResourceId, objects );

        this.context = context;
        sodaBases = (ArrayList<SodaBase>) objects;
    }

    private class ViewHolder
    {
        protected ImageView imageView;
        protected TextView textView;
    }

    @Override
    public Object getItem( int position )
    {
        return sodaBases.get( position );
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ViewHolder viewHolder;

        if ( convertView == null )
        {
            convertView = View.inflate( context, R.layout.soda_base_gallery_view, null );
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById( R.id.image );
            viewHolder.textView = (TextView) convertView.findViewById( R.id.text );
            convertView.setTag( viewHolder );
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText( sodaBases.get( position ).getName() );
        viewHolder.imageView.setImageResource( SodaBase.images.get( sodaBases.get( position ).getName() ) );

        return convertView;
    }

    @Override
    public int getCount()
    {
        return sodaBases.size();
    }
}
