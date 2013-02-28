package com.hjkatz.sodamixer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.hjkatz.sodamixer.R;
import com.hjkatz.sodamixer.fragment.CreateFragment;
import com.hjkatz.sodamixer.fragment.MixesFragment;
import java.util.ArrayList;

/** Created by User: Harrison Katz on Date: 1/9/13 */
public class MainActivity extends SherlockFragmentActivity
{

    private static ArrayList<Fragment> fragments = new ArrayList<>();

    static
    {
        //Maintain Same Order as Adding Tabs!!!!!!!!!
        fragments.add( new MixesFragment() );
        fragments.add( new CreateFragment() );
    }

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        this.setContentView( R.layout.launch_activity );

        MainPagerAdapter fragmentPagerAdapter = new MainPagerAdapter( getSupportFragmentManager() );
        fragmentPagerAdapter.setFragments( fragments );

        ViewPager viewPager = (ViewPager) findViewById( R.id.viewPager );
        viewPager.setAdapter( fragmentPagerAdapter );
        viewPager.setOnPageChangeListener( new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected( int position )
            {
                // When swiping between pages, select the
                // corresponding tab.
                getSupportActionBar().setSelectedNavigationItem( position );
            }
        } );

        final ActionBar bar = getSupportActionBar();
        bar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );
        bar.setDisplayOptions( 0, ActionBar.DISPLAY_USE_LOGO );

        //Add Tabs
        // Maintain Same Order as fragments!!!!!!!!!!!!!
        bar.addTab( bar.newTab().setText( "Mixes" ).setTabListener( new SodaTabListener<>( viewPager ) ) );
        bar.addTab( bar.newTab().setText( "Create" ).setTabListener( new SodaTabListener<>( viewPager ) ) );

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
        private ViewPager mPager;

        public SodaTabListener( ViewPager pager )
        {
            mPager = pager;
        }

        public void onTabSelected( ActionBar.Tab tab, FragmentTransaction ft )
        {
            mPager.setCurrentItem( tab.getPosition() );
        }

        @Override
        public void onTabUnselected( ActionBar.Tab tab, FragmentTransaction ft )
        {
        }

        @Override
        public void onTabReselected( ActionBar.Tab tab, FragmentTransaction ft )
        {
        }
    }

    private class MainPagerAdapter extends FragmentPagerAdapter
    {
        ArrayList<Fragment> mFragments;

        public MainPagerAdapter( FragmentManager fm )
        {
            super( fm );
        }

        public void setFragments( ArrayList<Fragment> fragments )
        {
            mFragments = fragments;
        }

        @Override
        public android.support.v4.app.Fragment getItem( int i )
        {
            return mFragments.get( i );
        }

        @Override
        public int getCount()
        {
            return mFragments.size();
        }
    }
}