package com.revature.orm.util;

import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.Table;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.Metamodel;
import com.revature.orm.model.PrimaryKeyField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * creates connections to the database
 * handles JDBC sql code?
 */
public class ConnectionUtilities
{
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtilities.class);

    private static Connection connection;

    public static void createConnection(String dbUrl, String username, String password)
    {
        try
        {
            connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.print(connection);
        }
        catch (SQLException e)
        {
            logger.warn(e.getMessage(), e);
        }
    }

    public static synchronized Connection getConnection()
    {
        return connection;
    }
}
