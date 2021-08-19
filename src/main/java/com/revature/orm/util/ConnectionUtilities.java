package com.revature.orm.util;

import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.Table;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.Metamodel;
import com.revature.orm.model.PrimaryKeyField;

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

    // TODO: 8/18/2021 see if can refactor with helper methods
    public void create(Object newRecord, Metamodel<?> metamodel)
    {
        Class<?> newRecordClass = metamodel.getAClass();

        String sqlStatement = "insert into \"" + newRecordClass.getAnnotation(Table.class).tableName() + "\"";

        List<ColumnField> columnFields = metamodel.getColumnFields();
        sqlStatement += "(" + columnFields.get(0);
        for(int i = 1; i < columnFields.size(); i++)
        {
            sqlStatement += ", \"" + columnFields.get(i).getTableColumnName() + "\"";
        }
        sqlStatement += ") values (? ";
        for(int i = 1; i < columnFields.size(); i++)
        {
            sqlStatement += ", ?";
        }
        sqlStatement += ")";

        PreparedStatement preparedStatement;
        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
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

    // TODO: 8/19/2021
    public List<?> read(Metamodel<?> metamodel)
    {
        Class<?> recordClass = metamodel.getAClass();

        String sqlStatement = "select * from \"" + recordClass.getAnnotation(Table.class).tableName();

        PreparedStatement preparedStatement;

        List<?> records = new ArrayList<>();

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            Constructor<?>[] constructors = recordClass.getDeclaredConstructors();
            Constructor<?> metamodelConstructor = null;
            for(Constructor<?> constructor : constructors)
            {
                if (constructor.getAnnotation(MetamodelConstructor.class) != null)
                {
                    metamodelConstructor = constructor;
                    break;
                }
            }

            Parameter[] constructorParameters = metamodelConstructor.getParameters();
            List<ColumnField> columnFields = metamodel.getColumnFields();
            List<ColumnField> sortedColumnFieldsInParameterOrder = new ArrayList<>();
            for(Parameter parameter : constructorParameters) 
            {
                for (ColumnField columnField : columnFields) 
                {
                    if(columnField.getClassFieldName().equals(parameter.getName())) 
                    {
                        sortedColumnFieldsInParameterOrder.add(columnField);
                        break;
                    }
                }
            }
            while(resultSet.next())
            {
                List<Object> arguments = new ArrayList<>();
                for(ColumnField columnField : sortedColumnFieldsInParameterOrder) 
                {
                    Type argumentType = columnField.getType();
                    Object record = resultSet.getObject(columnField.getTableColumnName());
                    arguments.add(argumentType.getClass().cast(record));
                }
                records.add(metamodelConstructor.newInstance(arguments));
            }
        }
        catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException throwables)
        {
            throwables.printStackTrace();
        }

        return records;
    }

    public void update(Object record, Metamodel<?> metamodel)
    {
        Class<?> recordClass = metamodel.getAClass();

        String sqlStatement = "update \"" + recordClass.getAnnotation(Table.class).tableName() + "\" set";

        List<ColumnField> columnFields = metamodel.getColumnFields();
        sqlStatement += " \"" + columnFields.get(0).getTableColumnName() + "\" = ?";
        for(int i = 1; i < columnFields.size(); i++)
        {
            sqlStatement += ", \"" + columnFields.get(i).getTableColumnName() + "\" = ?";
        }
        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKeyField();
        sqlStatement += " where \"" + primaryKeyField.getTableColumnName() + "\" = ?";

        PreparedStatement preparedStatement;
        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
            for(int i = 0; i < columnFields.size(); i++)
            {
                preparedStatement.setObject(i+1, columnFields.get(i).getField().get(record));
            }
            preparedStatement.setObject(columnFields.size()+1, primaryKeyField.getField().get(record));
            preparedStatement.execute();
        }
        catch (SQLException | IllegalAccessException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void delete(Object oldRecord, Metamodel<?>metamodel)
    {
        Class<?> recordClass = metamodel.getAClass();

        String sqlStatement = "delete from \"" + recordClass.getAnnotation(Table.class).tableName();

        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKeyField();
        sqlStatement += " where \"" + primaryKeyField.getTableColumnName() + "\" = ?";

        PreparedStatement preparedStatement;
        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setObject(1, primaryKeyField.getField().get(oldRecord));
            preparedStatement.execute();
        }
        catch (SQLException | IllegalAccessException throwables)
        {
            throwables.printStackTrace();
        }
    }
}
