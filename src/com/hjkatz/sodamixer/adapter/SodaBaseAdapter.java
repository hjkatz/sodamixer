package com.hjkatz.sodamixer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.model.SodaBase;
import java.util.ArrayList;
import java.util.List;

/** Created By: harrison on Date: 3/2/13 */
public class SodaBaseAdapter extends ArrayAdapter
{

    private ArrayList<SodaBase> sodaBases;
    private Context             context;

    public SodaBaseAdapter( Context context, int textViewResourceId, List objects )
    {
        super( context, textViewResourceId, objects );

        this.context = context;
        sodaBases = (ArrayList<SodaBase>) objects;
    }

    private class ViewHolder
    {
        protected ImageView imageView;
        protected TextView  textView;
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
        viewHolder.imageView.setImageResource( context.getResources().getIdentifier( sodaBases.get( position ).getIcon(), "drawable", context.getPackageName() ) );
        return convertView;
    }

    @Override
    public int getCount()
    {
        return sodaBases.size();
    }
}
