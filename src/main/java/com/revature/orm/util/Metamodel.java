package com.revature.orm.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class for converting between objects and database records
 * @param <E>
 */
// TODO: 8/17/2021 Rename class?
public class Metamodel<E>
{
    private Class<E> aClass;




    private void createMappings(List<String> columns, List<String> fields)
    {
        for(int i = 0; i < fields.size(); i++)
        {
            fieldToColumnMap.putIfAbsent(fields.get(i), columns.get(i));
            columnToFieldMap.putIfAbsent(columns.get(i), fields.get(i));
        }
    }

    // TODO: 8/17/2021 figure out parameters (ResultSet?)
    private E convertToObject(ResultSet resultSet) throws SQLException
    {

        if(resultSet.next())
        {

        }
        return null;
    }

    // TODO: 8/17/2021 figure out return type (List of columns?)
    private E convertToDatabaseRecord(E object)
    {
        return null;
    }

    public E retrieve()
    {
        return null;
    }

    public void create(E recordToCreate)
    {

    }

    public void delete(E recordToDelete)
    {

    }

    public void update(E recordToUpdate)
    {

    }
}