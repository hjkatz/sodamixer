package com.hjkatz.sodamixer.model;

import com.hjkatz.sodamixer.*;

import java.util.*;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaFlavor
{
    private Integer id;
    private String name;
    public static Map<String, Integer> flavorNames;

    static
    {
        flavorNames = new HashMap<String, Integer>();
        flavorNames.put( "cherry", R.string.cherry );
        flavorNames.put( "cherry_vanilla", R.string.cherry_vanilla );
        flavorNames.put( "fruit_punch", R.string.fruit_punch );
        flavorNames.put( "grape", R.string.grape );
        flavorNames.put( "lemon", R.string.lemon );
        flavorNames.put( "lime", R.string.lime );
        flavorNames.put( "orange", R.string.orange );
        flavorNames.put( "orange_vanilla", R.string.orange_vanilla );
        flavorNames.put( "peach", R.string.peach );
        flavorNames.put( "raspberry", R.string.raspberry );
        flavorNames.put( "regular", R.string.regular );
        flavorNames.put( "strawberry", R.string.strawberry );
        flavorNames.put( "vanilla", R.string.vanilla );
    }

    public void SodaFlavor()
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
