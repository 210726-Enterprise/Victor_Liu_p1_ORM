package com.revature.orm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

/**
 * creates connections to the database
 */
public class ConnectionUtilities
{
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtilities.class);

    private static Connection connection;

    public static boolean createConnection(String dbUrl, String username, String password)
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbUrl, username, password);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            logger.warn(e.getMessage(), e);
        }
        return connection != null;
    }

    public static synchronized Connection getConnection()
    {
        return connection;
    }
}
