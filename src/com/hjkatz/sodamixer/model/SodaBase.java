package com.hjkatz.sodamixer.model;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class SodaBase
{

    private Integer id;
    private String  name;
    private String  name_formatted;
    private String  icon;

    public void SodaBase()
    {
        id = null;
        name = null;
        name_formatted = null;
        icon = null;
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

    public String getNameFormatted()
    {
        return name_formatted;
    }

    public void setNameFormatted( String name_formatted )
    {
        this.name_formatted = name_formatted;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon( String icon )
    {
        this.icon = icon;
    }
}
