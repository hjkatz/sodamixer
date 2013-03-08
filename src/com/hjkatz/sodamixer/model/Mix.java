package com.hjkatz.sodamixer.model;

import java.util.ArrayList;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class Mix
{
    private Integer          id;
    private String           name;
    private String           description;
    private Integer          rating;
    private ArrayList<Style> styles;
    private SodaBase         sodaBase;
    private SodaFlavor       sodaFlavor;

    public void Mix()
    {
        id = null;
        name = null;
        description = null;
        rating = null;
        styles = null;
        sodaBase = null;
        sodaFlavor = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating( Integer rating )
    {
        this.rating = rating;
    }

    public ArrayList<Style> getStyles()
    {
        return styles;
    }

    public void setStyles( ArrayList<Style> styles )
    {
        this.styles = styles;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public SodaBase getSodaBase()
    {
        return sodaBase;
    }

    public void setSodaBase( SodaBase sodaBase )
    {
        this.sodaBase = sodaBase;
    }

    public SodaFlavor getSodaFlavor()
    {
        return sodaFlavor;
    }

    public void setSodaFlavor( SodaFlavor sodaFlavor )
    {
        this.sodaFlavor = sodaFlavor;
    }
}
