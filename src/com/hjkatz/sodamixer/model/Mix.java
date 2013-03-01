package com.hjkatz.sodamixer.model;

import java.util.ArrayList;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class Mix
{

    private String name;
    private String descriptiong;
    private String rating;
    private ArrayList<Style> styles;
    private ArrayList<Soda> sodas;

    public void Mix()
    {
        name = "";
        descriptiong = "";
        rating = "";
        styles = new ArrayList<>();
        sodas = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescriptiong()
    {
        return descriptiong;
    }

    public void setDescriptiong( String descriptiong )
    {
        this.descriptiong = descriptiong;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating( String rating )
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
}
