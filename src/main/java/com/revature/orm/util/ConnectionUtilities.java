package com.revature.orm.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * creates connections to the database
 * handles JDBC sql code?
 */
public class ConnectionUtilities
{
    private Connection connection;

    public void createConnection(String dbUrl, String username, String password)
    {
        try
        {
            connection = DriverManager.getConnection(dbUrl, username, password);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void create(Object newRecord, String table)
    {
        String sqlStatement = "insert into \"" + table + "\"";

        Class newRecordClass = newRecord.getClass();
        Field[] numberOfFields = newRecordClass.getDeclaredFields();
        for(Field field : numberOfFields)
        {
            sqlStatement += " " + field.getName();
        }
        sqlStatement += " values (? ";
        for(int i = 1; i < numberOfFields.length; i++)
        {
            sqlStatement += ", ?";
        }
        sqlStatement += ")";

        PreparedStatement preparedStatement;

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
            for(Field field : numberOfFields)
            {
                switch (field.getType().getSimpleName())
                {
                    case "int":
                    {

                    }
                    default:
                }
            }
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
