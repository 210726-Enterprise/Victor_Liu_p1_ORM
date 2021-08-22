package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.orm.util.ConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VideoGameService
{
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);

    private ObjectMapper objectMapper;

    public VideoGameService()
    {
        objectMapper = new ObjectMapper();
    }

    public void getVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {

    }

    public void insertVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {

    }

    public void updateVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {

    }

    public void deleteVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {

    }
}
