package com.revature.orm.util;

import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.Table;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.Metamodel;

import java.lang.reflect.*;
import java.sql.*;
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

    // TODO: 8/18/2021 see if can refactor with helper methods 
    // TODO: 8/19/2021 get metamodel from newRecord.getClass()
    public void create(Object newRecord, Metamodel metamodel)
    {
        Class<?> newRecordClass = metamodel.getAClass();

        String sqlStatement = "insert into \"" + newRecordClass.getAnnotation(Table.class).tableName() + "\"";

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

    // TODO: 8/19/2021 set records list type 
    public void read(Metamodel metamodel)
    {
        Class<?> recordClass = metamodel.getAClass();

        String sqlStatement = "select * from \"" + recordClass.getAnnotation(Table.class).tableName();

        PreparedStatement preparedStatement;

        List records = new ArrayList();

        try
        {
            preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            Constructor[] constructors = recordClass.getDeclaredConstructors();
            Constructor metamodelConstructor = null;
            for(Constructor constructor : constructors)
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
                        continue;
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
        
        
    }
}
