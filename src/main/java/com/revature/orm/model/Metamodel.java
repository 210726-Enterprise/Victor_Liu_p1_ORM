package com.revature.orm.model;

import com.revature.orm.annotations.Column;
import com.revature.orm.annotations.MetamodelConstructor;
import com.revature.orm.annotations.PrimaryKey;
import com.revature.orm.model.ColumnField;
import com.revature.orm.model.PrimaryKeyField;

import java.lang.reflect.*;
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

    // TODO: 8/18/2021 move to new service class?
    private E convertToObject(ResultSet resultSet) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException 
    {
        if(resultSet.next())
        {
            Constructor[] constructors = aClass.getDeclaredConstructors();
            for(Constructor constructor : constructors)
            {
                if(constructor.getAnnotation(MetamodelConstructor.class) != null)
                {
                    Parameter[] constructorParameters = constructor.getParameters();
                    List<Object> argumentList = getArgumentList(constructorParameters, resultSet);
                    return (E) constructor.newInstance(argumentList);
                }
            }
        }
        return null;
    }

    private List<Object> getArgumentList(Parameter[] parameters, ResultSet resultSet) throws SQLException
    {
        List<Object> arguments = new ArrayList<>();
        for(Parameter parameter : parameters)
        {
            for(ColumnField columnField : columnFields)
            {
                if(columnField.getClassFieldName().equals(parameter.getName()))
                {
                    Type argumentType = columnField.getType();
                    Object record = resultSet.getObject(columnField.getTableColumnName());
                    arguments.add(argumentType.getClass().cast(record));
                }
            }
        }
        return arguments;
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
