package com.hjkatz.sodamixer.model;

import com.hjkatz.sodamixer.*;

import java.util.*;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaBase
{
    private Integer id;
    private String name;
    public static Map<String, Integer> images;

    static
    {
        images = new HashMap<String, Integer>();
        images.put( "barqs", R.drawable.ic_barqs );
        images.put( "barqs_diet", R.drawable.ic_barqs_diet );
        images.put( "coke", R.drawable.ic_coke );
        images.put( "coke_caffeine_free", R.drawable.ic_coke_caffeine_free );
        images.put( "coke_diet", R.drawable.ic_coke_diet );
        images.put( "coke_zero", R.drawable.ic_coke_zero );
        images.put( "dr_peppere", R.drawable.ic_dr_pepper );
        images.put( "dr_pepper_diet", R.drawable.ic_dr_pepper_diet );
        images.put( "fanta", R.drawable.ic_fanta );
        images.put( "fanta_zero", R.drawable.ic_fanta_zero );
        images.put( "hic", R.drawable.ic_hic );
        images.put( "mellow_yellow", R.drawable.ic_mellow_yellow );
        images.put( "minute_maid_lemonade", R.drawable.ic_minute_maid_lemonade );
        images.put( "minute_maid_light", R.drawable.ic_minute_maid_light );
        images.put( "pibb_xtra", R.drawable.ic_pibb_xtra );
        images.put( "pibb_zero", R.drawable.ic_pibb_zero );
        images.put( "powerade", R.drawable.ic_powerade );
        images.put( "powerade_zero", R.drawable.ic_powerade_zero );
        images.put( "sprite", R.drawable.ic_sprite );
        images.put( "sprite_zero", R.drawable.ic_sprite_zero );
    }

    public void SodaBase()
    {
        id = null;
        name = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }
}
