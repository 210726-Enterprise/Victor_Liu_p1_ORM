package com.revature.orm.util;

import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.Table;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.Metamodel;
import com.revature.orm.model.PrimaryKeyField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DMLMethods
{
    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtilities.class);

    // TODO: 8/18/2021 see if can refactor with helper methods
    public <T> boolean create(Object newRecord, Metamodel<T> metamodel)
    {
        Class<T> newRecordClass = metamodel.getAClass();

        String sqlStatement = "insert into \"" + newRecordClass.getAnnotation(Table.class).tableName() + "\" ";

        List<ColumnField> columnFields = metamodel.getColumnFields();
        sqlStatement += "(\"" + columnFields.get(0).getTableColumnName() + "\"";
        for(int i = 1; i < columnFields.size(); i++)
        {
            sqlStatement += ", \"" + columnFields.get(i).getTableColumnName() + "\"";
        }
        sqlStatement += ") values (?";
        for(int i = 1; i < columnFields.size(); i++)
        {
            sqlStatement += ", ?";
        }
        sqlStatement += ")";

        PreparedStatement preparedStatement;
        try
        {
            Connection connection = ConnectionUtilities.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            for(int i = 0; i < columnFields.size(); i++)
            {
                Field columnField = columnFields.get(i).getField();
                columnField.setAccessible(true);
                preparedStatement.setObject(i+1, columnField.get(newRecord));
            }
            int result = preparedStatement.executeUpdate();
            return result != 0;
        }
        catch (SQLException | IllegalAccessException e)
        {
            logger.warn(e.getMessage(), e);
        }
        return false;
    }

    public <T> List<T> read(Metamodel<T> metamodel)
    {
        Class<T> recordClass = metamodel.getAClass();

        String sqlStatement = "select * from \"" + recordClass.getAnnotation(Table.class).tableName() + "\"";

        PreparedStatement preparedStatement;

        List<T> records = new ArrayList<>();

        try
        {
            Connection connection = ConnectionUtilities.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            Constructor<?> metamodelConstructor = metamodel.getMetamodelConstructor();

            List<ColumnField> columnFields = metamodel.getColumnFields();

            while(resultSet.next())
            {
                List<Object> arguments = new ArrayList<>();
                for(ColumnField columnField : columnFields)
                {
                    Object record = resultSet.getObject(columnField.getTableColumnName());
                    arguments.add(record);
                }
                records.add(recordClass.cast(metamodelConstructor.newInstance(arguments.toArray())));
            }
        }
        catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            logger.warn(e.getMessage(), e);
        }

        return records;
    }

    public <T> boolean update(Object record, Metamodel<T> metamodel)
    {
        Class<T> recordClass = metamodel.getAClass();

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
            Connection connection = ConnectionUtilities.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            for(int i = 0; i < columnFields.size(); i++)
            {
                preparedStatement.setObject(i+1, columnFields.get(i).getField().get(record));
            }
            preparedStatement.setObject(columnFields.size()+1, primaryKeyField.getField().get(record));
            int result = preparedStatement.executeUpdate();
            return result != 0;
        }
        catch (SQLException | IllegalAccessException e)
        {
            logger.warn(e.getMessage(), e);
        }
        return false;
    }

    public <T> boolean delete(Object oldRecord, Metamodel<T> metamodel)
    {
        Class<T> recordClass = metamodel.getAClass();

        String sqlStatement = "delete from \"" + recordClass.getAnnotation(Table.class).tableName();

        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKeyField();
        sqlStatement += " where \"" + primaryKeyField.getTableColumnName() + "\" = ?";

        PreparedStatement preparedStatement;
        try
        {
            Connection connection = ConnectionUtilities.getConnection();
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setObject(1, primaryKeyField.getField().get(oldRecord));
            int result = preparedStatement.executeUpdate();
            return result != 0;
        }
        catch (SQLException | IllegalAccessException e)
        {
            logger.warn(e.getMessage(), e);
        }
        return false;
    }
}
