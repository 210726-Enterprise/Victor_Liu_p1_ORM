package com.revature.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoGameModelTest
{
    VideoGame testVideoGame;

    @BeforeEach
    void setUp()
    {
        testVideoGame = new VideoGame(0, "testname", "testdev", "testpublish", "testgenre");
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void getId()
    {
        assertEquals(testVideoGame.getId(), 0);
    }

    @Test
    void setId()
    {
        testVideoGame.setId(2);
        assertEquals(testVideoGame.getId(), 2);
    }

    @Test
    void getName()
    {
        assertEquals(testVideoGame.getName(), "testname");
    }

    @Test
    void setName()
    {
        testVideoGame.setName("name2");
        assertEquals(testVideoGame.getName(), "name2");
    }

    @Test
    void getDeveloper()
    {
        assertEquals(testVideoGame.getDeveloper(), "testdev");
    }

    @Test
    void setDeveloper()
    {
        testVideoGame.setDeveloper("dev2");
        assertEquals(testVideoGame.getDeveloper(), "dev2");
    }

    @Test
    void getPublisher()
    {
        assertEquals(testVideoGame.getPublisher(), "testpublish");
    }

    @Test
    void setPublisher()
    {
        testVideoGame.setPublisher("publish2");
        assertEquals(testVideoGame.getPublisher(), "publish2");
    }

    @Test
    void getGenre()
    {
        assertEquals(testVideoGame.getGenre(), "testgenre");
    }

    @Test
    void setGenre()
    {
        testVideoGame.setGenre("genre2");
        assertEquals(testVideoGame.getGenre(), "genre2");
    }

    @Test
    void testToString()
    {
        assertEquals(testVideoGame.toString(),
                "Video Game{" +
                "id=" + 0 +
                ", name='testname'" +
                ", developer='testdev'" +
                ", publisher='testpublish'" +
                ", genre='testgenre'" +
                '}');
    }

    @Test
    void idConstructorTest()
    {
        VideoGame testVideoGame = new VideoGame(100);
        assertEquals(testVideoGame.getId(), 100);
        assertEquals(testVideoGame.getName(), "");
        assertEquals(testVideoGame.getDeveloper(), "");
        assertEquals(testVideoGame.getPublisher(), "");
        assertEquals(testVideoGame.getGenre(), "");
    }

    @Test
    void defaultConstructorTest()
    {
        VideoGame testVideoGame = new VideoGame();
        assertEquals(testVideoGame.getId(), 0);
        assertEquals(testVideoGame.getName(), "");
        assertEquals(testVideoGame.getDeveloper(), "");
        assertEquals(testVideoGame.getPublisher(), "");
        assertEquals(testVideoGame.getGenre(), "");
    }
}