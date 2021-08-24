import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.model.VideoGame;
import com.revature.orm.util.ORM;
import com.revature.service.VideoGameService;
import com.revature.servlet.VideoGameServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ServletDriver implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        List<Class> classList = new ArrayList<>();
        classList.add(VideoGame.class);
        ORM videoGameORM = new ORM(
                "jdbc:postgresql://revature-assignment-db.c4c3no36zu7c.us-east-2.rds.amazonaws.com:5432/postgres",
                "postgres",
                "rI3VK00VlEcazaJ8XXoE",
                classList);
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        VideoGameService videoGameService = new VideoGameService(videoGameORM, objectMapper);

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("videoGameServlet", new VideoGameServlet(videoGameService)).addMapping("/videogames");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
