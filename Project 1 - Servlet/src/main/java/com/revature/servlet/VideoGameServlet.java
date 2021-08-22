package com.revature.servlet;

import com.revature.service.VideoGameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/videogames")
public class VideoGameServlet extends HttpServlet
{
    VideoGameService videoGameService;

    public VideoGameServlet()
    {
        // TODO: 8/22/2021 put in dbinfo in constructor
//        this.videoGameService = new VideoGameService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.getVideoGame(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.insertVideoGame(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.updateVideoGame(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.deleteVideoGame(req, resp);
    }
}
