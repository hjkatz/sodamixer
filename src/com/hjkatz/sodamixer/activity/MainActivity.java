package com.hjkatz.sodamixer.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.hjkatz.sodamixer.R;
import fragment.CreateFragment;
import fragment.MixesFragment;

/** Created by User: Harrison Katz on Date: 1/9/13 */
public class MainActivity extends Activity
{
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.launch_activity );

        final ActionBar bar = getActionBar();
        bar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );
        bar.setDisplayOptions( 0, ActionBar.DISPLAY_USE_LOGO );

        //Add Tabs
        bar.addTab( bar.newTab().setText( "Create" ).setTabListener( new SodaTabListener<>( this, "create", CreateFragment.class ) ) );
        bar.addTab( bar.newTab().setText( "Mixes" ).setTabListener( new SodaTabListener<>( this, "mixes", MixesFragment.class ) ) );

        if ( savedInstanceState != null )
        {
            bar.setSelectedNavigationItem( savedInstanceState.getInt( "tab", 0 ) );
        }
    }

    @Override
    protected void onSaveInstanceState( Bundle outState )
    {
        super.onSaveInstanceState( outState );
        outState.putInt( "tab", getActionBar().getSelectedNavigationIndex() );
    }

    public static class SodaTabListener<T extends Fragment> implements ActionBar.TabListener
    {
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final Bundle mArgs;
        private Fragment mFragment;

        public SodaTabListener( Activity activity, String tag, Class<T> clz )
        {
            this( activity, tag, clz, null );
        }

        public SodaTabListener( Activity activity, String tag, Class<T> clz, Bundle args )
        {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            mFragment = mActivity.getFragmentManager().findFragmentByTag( mTag );
            if ( mFragment != null && !mFragment.isDetached() )
            {
                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
                ft.detach( mFragment );
                ft.commit();
            }
        }

        public void onTabSelected( ActionBar.Tab tab, FragmentTransaction ft )
        {
            if ( mFragment == null )
            {
                mFragment = Fragment.instantiate( mActivity, mClass.getName(), mArgs );
                ft.add( android.R.id.content, mFragment, mTag );
            }
            else
            {
                ft.attach( mFragment );
            }
        }

        public void onTabUnselected( ActionBar.Tab tab, FragmentTransaction ft )
        {
            if ( mFragment != null )
            {
                ft.detach( mFragment );
            }
        }

        public void onTabReselected( ActionBar.Tab tab, FragmentTransaction ft )
        {
        }
    }
}