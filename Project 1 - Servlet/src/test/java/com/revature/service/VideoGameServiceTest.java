package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.revature.model.VideoGame;
import com.revature.orm.util.ORM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;

class VideoGameServiceTest
{
    ObjectMapper mockMapper;
    ObjectWriter mockWriter;
    BufferedReader mockBufferedReader;
    ORM mockORM;
    HttpServletRequest mockRequest;
    HttpServletResponse mockResponse;
    ServletOutputStream mockOutputStream;


    List<VideoGame> videoGameList;
    String mockJSON;

    VideoGameService testVideoGameService;

    @BeforeEach
    void setUp()
    {
        mockMapper = mock(ObjectMapper.class);
        mockWriter = mock(ObjectWriter.class);
        mockORM = mock(ORM.class);
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockOutputStream = mock(ServletOutputStream.class);
        mockBufferedReader = mock(BufferedReader.class);

        videoGameList = new ArrayList<>();
        mockJSON = "test JSON";

        testVideoGameService = new VideoGameService(mockORM, mockMapper);
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void getVideoGameSuccess() throws IOException
    {
        videoGameList.add(new VideoGame());
        when(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockWriter);
        when(mockMapper.writerWithDefaultPrettyPrinter().writeValueAsString(videoGameList)).thenReturn(mockJSON);
        when(mockResponse.getOutputStream()).thenReturn(mockOutputStream);
        when(mockORM.getRecords("videogames")).thenReturn(videoGameList);

        testVideoGameService.getVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_OK);
        verify(mockResponse.getOutputStream()).print(mockJSON);
    }

    @Test
    void getVideoGameFail() throws IOException
    {
        videoGameList.add(new VideoGame());
        when(mockORM.getRecords("videogames")).thenReturn(videoGameList);
        when(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockWriter);
        when(mockResponse.getOutputStream()).thenThrow(new IOException());

        testVideoGameService.getVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CONFLICT);
    }

    @Test
    void insertVideoGameSuccess() throws IOException
    {
        VideoGame mockVideoGame = new VideoGame();
        String mockString = "{" +
                "\"id\" : 0," +
                "\"name\" : \"test\"," +
                "\"developer\" : \"test\"," +
                "\"publisher\" : \"test\"," +
                "\"genre\" : \"test\"" +
                "}";
        Stream<String> mockLineStream = Arrays.stream(new String[]{mockString});
        when(mockRequest.getReader()).thenReturn(mockBufferedReader);
        when(mockRequest.getReader().lines()).thenReturn(mockLineStream);
        when(mockMapper.readValue(mockString, VideoGame.class)).thenReturn(mockVideoGame);
        when(mockORM.addRecord(mockVideoGame)).thenReturn(false);

        testVideoGameService.insertVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CONFLICT);
    }

    @Test
    void insertVideoGameFail() throws IOException
    {
        VideoGame mockVideoGame = new VideoGame();
        String mockString = "{" +
                "\"id\" : 0," +
                "\"name\" : \"test\"," +
                "\"developer\" : \"test\"," +
                "\"publisher\" : \"test\"," +
                "\"genre\" : \"test\"" +
                "}";
        Stream<String> mockLineStream = Arrays.stream(new String[]{mockString});
        when(mockRequest.getReader()).thenReturn(mockBufferedReader);
        when(mockRequest.getReader().lines()).thenReturn(mockLineStream);
        when(mockMapper.readValue(mockString, VideoGame.class)).thenReturn(mockVideoGame);
        when(mockORM.addRecord(mockVideoGame)).thenReturn(true);

        testVideoGameService.insertVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    void insertVideoGameExceptionThrown() throws IOException
    {
        when(mockRequest.getReader()).thenThrow(new IOException());

        testVideoGameService.insertVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void updateVideoGameSuccess() throws IOException
    {
        VideoGame mockVideoGame = new VideoGame();
        String mockString = "{" +
                "\"id\" : 0," +
                "\"name\" : \"test\"," +
                "\"developer\" : \"test\"," +
                "\"publisher\" : \"test\"," +
                "\"genre\" : \"test\"" +
                "}";
        Stream<String> mockLineStream = Arrays.stream(new String[]{mockString});
        when(mockRequest.getReader()).thenReturn(mockBufferedReader);
        when(mockRequest.getReader().lines()).thenReturn(mockLineStream);
        when(mockMapper.readValue(mockString, VideoGame.class)).thenReturn(mockVideoGame);
        when(mockORM.updateRecord(mockVideoGame)).thenReturn(true);

        testVideoGameService.updateVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    void updateVideoGameFail() throws IOException
    {
        VideoGame mockVideoGame = new VideoGame();
        String mockString = "{" +
                "\"id\" : 0," +
                "\"name\" : \"test\"," +
                "\"developer\" : \"test\"," +
                "\"publisher\" : \"test\"," +
                "\"genre\" : \"test\"" +
                "}";
        Stream<String> mockLineStream = Arrays.stream(new String[]{mockString});
        when(mockRequest.getReader()).thenReturn(mockBufferedReader);
        when(mockRequest.getReader().lines()).thenReturn(mockLineStream);
        when(mockMapper.readValue(mockString, VideoGame.class)).thenReturn(mockVideoGame);
        when(mockORM.updateRecord(mockVideoGame)).thenReturn(false);

        testVideoGameService.updateVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CONFLICT);
    }

    @Test
    void updateVideoGameExceptionThrown() throws IOException
    {
        when(mockRequest.getReader()).thenThrow(new IOException());

        testVideoGameService.updateVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void deleteVideoGameSuccess()
    {
        when(mockRequest.getParameter("id")).thenReturn("0");
        when(mockORM.deleteRecord(any())).thenReturn(true);

        testVideoGameService.deleteVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CREATED);
    }

    @Test
    void deleteVideoGameFail()
    {
        when(mockRequest.getParameter("id")).thenReturn("-1");
        when(mockORM.deleteRecord(any())).thenReturn(false);

        testVideoGameService.deleteVideoGame(mockRequest, mockResponse);

        verify(mockResponse).setStatus(HttpServletResponse.SC_CONFLICT);
    }
}