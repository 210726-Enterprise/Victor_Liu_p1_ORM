package com.revature.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.PrimaryKey;
import com.revature.orm.annotations.Table;

@Table(tableName = "VideoGames")
public class VideoGame
{
    @Column(columnName = "id")
    @PrimaryKey(primaryKeyName = "id")
    private int id;

    @Column(columnName = "name")
    private String name;

    @Column(columnName = "developer")
    private String developer;

    @Column(columnName = "publisher")
    private String publisher;

    @Column(columnName = "genre")
    private String genre;

    @MetamodelConstructor
    public VideoGame(int id, String name, String developer, String publisher, String genre)
    {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.genre = genre;
    }

    public VideoGame(int id)
    {
        this.id = id;
        this.name = "";
        this.developer = "";
        this.publisher = "";
        this.genre = "";
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    @Override
    public String toString()
    {
        return "Video Game{" +
                "id=" + id +
                ", name='" + name +
                ", developer='" + developer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
