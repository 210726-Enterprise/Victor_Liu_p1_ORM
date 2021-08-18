package com.revature.orm.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.PrimaryKey;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.PrimaryKeyField;

import java.lang.annotation.Annotation;
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
    private PrimaryKeyField primaryKeyField;
    private List<ColumnField> columnFields;

    public Metamodel(Class aClass)
    {
        aClass = aClass;
        createMappings();
    }

    private void createMappings()
    {
        columnFields = new ArrayList<>();
        Field[] fields = aClass.getDeclaredFields();
        for(Field field: fields)
        {
            if(field.getAnnotation(PrimaryKey.class) != null)
            {
                primaryKeyField = new PrimaryKeyField(field);
            }
            else if(field.getAnnotation(Column.class) != null)
            {
                columnFields.add(new ColumnField(field));
            }
        }
    }


    // TODO: 8/17/2021 figure out parameters (ResultSet?)
    private E convertToObject(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
        {
            Object primaryKey = resultSet.getObject(primaryKeyField.getTableColumnName());

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
