package com.revature.orm.util;

import com.revature.orm.annotations.Column;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.Metamodel;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // TODO: 8/17/2021 figure out return type (List of columns?)
    private List<Object> convertToDatabaseRecord(Object object)
    {
        List<Object> columns = new ArrayList<>();
        Field[] attributes = object.getClass().getDeclaredFields();
        for(Field field : attributes)
        {

        }
        return null;
    }

    public void create(Object newRecord, Metamodel metamodel, String table)
    {
        String sqlStatement = "insert into \"" + table + "\"";

        Class newRecordClass = metamodel.getAClass();
        List<ColumnField> columnFields = metamodel.getColumnFields();
        for(ColumnField columnField : columnFields)
        {
            sqlStatement += " " + columnField.getTableColumnName();
        }
        sqlStatement += " values (? ";
        for(int i = 1; i < columnFields.size(); i++)
        {
            sqlStatement += ", ?";
        }
        sqlStatement += ")";

        PreparedStatement preparedStatement;
        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
            List<Field> fields = Arrays.asList(newRecord.getClass().getDeclaredFields());
            for(int i = 0; i < columnFields.size(); i++)
            {
                preparedStatement.setObject(i+1, columnFields.get(i).getField().get(newRecord));
            }
            preparedStatement.execute();
        }
        catch (SQLException | IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
