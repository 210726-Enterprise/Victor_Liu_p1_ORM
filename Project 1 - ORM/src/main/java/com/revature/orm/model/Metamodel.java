package com.revature.orm.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.PrimaryKey;

import java.lang.reflect.*;
import java.util.*;

/**
 * Class for converting between objects and database records
 * @param <E>
 */
// TODO: 8/17/2021 Rename class?
public class Metamodel<E>
{
    private final Class<E> aClass;
    private PrimaryKeyField primaryKeyField;
    private List<ColumnField> columnFields;

    public Metamodel(Class<E> aClass)
    {
        this.aClass = aClass;
        createMappings();
    }

    public Class<E> getAClass()
    {
        return aClass;
    }

    public PrimaryKeyField getPrimaryKeyField()
    {
        return primaryKeyField;
    }

    public List<ColumnField> getColumnFields()
    {
        return columnFields;
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
}