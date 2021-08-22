package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.VideoGame;
import com.revature.orm.util.ConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class VideoGameService
{
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);

    private ObjectMapper objectMapper;
    private ConfigBuilder orm;

    public VideoGameService(String dbUrl, String username, String password)
    {
        objectMapper = new ObjectMapper();
    }

    public void getVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orm.getRecords("VideoGames"));
            resp.getOutputStream().print(json);

        }
        catch (IOException e)
        {
            logger.warn(e.getMessage(), e);
        }
    }

    public void insertVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            VideoGame newVideoGame = objectMapper.readValue(builder.toString(), VideoGame.class);
            boolean result = orm.addRecord(newVideoGame);

            if(result)
            {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        }
        catch (Exception e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn(e.getMessage());
        }
    }

    public void updateVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            VideoGame newVideoGame = objectMapper.readValue(builder.toString(), VideoGame.class);
            boolean result = orm.updateRecord(newVideoGame);

            if(result)
            {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        }
        catch (Exception e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn(e.getMessage());
        }
    }

    public void deleteVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            VideoGame newVideoGame = objectMapper.readValue(builder.toString(), VideoGame.class);
            boolean result = orm.deleteRecord(newVideoGame);

            if(result)
            {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        }
        catch (Exception e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn(e.getMessage());
        }
    }
}
