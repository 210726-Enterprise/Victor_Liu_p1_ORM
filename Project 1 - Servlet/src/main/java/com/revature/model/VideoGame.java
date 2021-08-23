package com.revature.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.PrimaryKey;
import com.revature.orm.annotations.Table;

@Table(tableName = "videogames")
public class VideoGame
{
    @Column(columnName = "id", parameterNumber = 0)
    @PrimaryKey(primaryKeyName = "id")
    private int id;

    @Column(columnName = "name", parameterNumber = 1)
    private String name;

    @Column(columnName = "developer", parameterNumber = 2)
    private String developer;

    @Column(columnName = "publisher", parameterNumber = 3)
    private String publisher;

    @Column(columnName = "genre", parameterNumber = 4)
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

    public VideoGame()
    {
        this.id = 0;
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
                ", name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
