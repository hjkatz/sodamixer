package com.hjkatz.sodamixer.model;

/** Created By: Harrison Katz on Date: 2/28/13 */
public class Style
{
    private Integer id;
    private String  nameFormatted;

    public void Style()
    {
        id = null;
        nameFormatted = null;
    }

    public String getNameFormatted()
    {
        return nameFormatted;
    }

    public void setNameFormatted( String nameFormatted )
    {
        this.nameFormatted = nameFormatted;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( obj == this )
        {
            return true;
        }
        if ( !( obj instanceof Style ) )
        {
            return false;
        }

        Style o = (Style) obj;
        return o.getId().equals( this.getId() );
    }
}
