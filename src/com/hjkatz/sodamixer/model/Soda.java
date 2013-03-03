package com.hjkatz.sodamixer.model;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class Soda
{
    private Integer id;
    private String name;
    private SodaBase sodaBase;
    private SodaFlavor sodaFlavor;

    public void Soda()
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

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }
}
