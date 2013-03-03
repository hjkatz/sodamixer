package com.hjkatz.sodamixer.model;

import java.util.ArrayList;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class Mix
{
    private Integer id;
    private String name;
    private String description;
    private Integer rating;
    private ArrayList<Style> styles;
    private ArrayList<Soda> sodas;

    public void Mix()
    {
        id = null;
        name = null;
        description = null;
        rating = null;
        styles = null;
        sodas = null;
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

    public ArrayList<Soda> getSodas()
    {
        return sodas;
    }

    public void setSodas( ArrayList<Soda> sodas )
    {
        this.sodas = sodas;
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
