package com.revature.servlet;

import com.revature.service.VideoGameService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

class VideoGameServletTest
{
    VideoGameService mockVideoGameService;
    HttpServletRequest mockRequest;
    HttpServletResponse mockResponse;

    VideoGameServlet testVideoGameServlet;

    @BeforeEach
    void setUp()
    {
        mockVideoGameService = mock(VideoGameService.class);
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);

        testVideoGameServlet = new VideoGameServlet(mockVideoGameService);
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void doGet() throws ServletException, IOException
    {
        testVideoGameServlet.doGet(mockRequest, mockResponse);
        verify(mockVideoGameService).getVideoGame(mockRequest, mockResponse);
    }

    @Test
    void doPost() throws ServletException, IOException
    {
        testVideoGameServlet.doPost(mockRequest, mockResponse);
        verify(mockVideoGameService).insertVideoGame(mockRequest, mockResponse);
    }

    @Test
    void doPut() throws ServletException, IOException
    {
        testVideoGameServlet.doPut(mockRequest, mockResponse);
        verify(mockVideoGameService).updateVideoGame(mockRequest, mockResponse);
    }

    @Test
    void doDelete() throws ServletException, IOException
    {
        testVideoGameServlet.doDelete(mockRequest, mockResponse);
        verify(mockVideoGameService).deleteVideoGame(mockRequest, mockResponse);
    }
}