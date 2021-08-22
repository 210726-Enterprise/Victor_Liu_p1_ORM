package com.revature.servlet;

import com.revature.orm.util.ORM;
import com.revature.service.VideoGameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(urlPatterns = "/videogames")
public class VideoGameServlet extends HttpServlet
{
    VideoGameService videoGameService;

    public VideoGameServlet()
    {
        this.videoGameService = new VideoGameService(
                "jdbc:postgresql://project0.c4c3no36zu7c.us-east-2.rds.amazonaws.com:5432/",
                "postgres",
                "rI3VK00VlEcazaJ8XXoE");
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
