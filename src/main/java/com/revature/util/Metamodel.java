package com.revature.util;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Class for converting between objects and database records
 * @param <E>
 */
// TODO: 8/17/2021 Rename class?
public class Metamodel<E>
{
    private Class<E> aClass;
    private String table;
    private Map<Field, List<?>> idField;
    private Map<Field, List<?>> columnFields;

    public Metamodel(Class<E> aClass, String table)
    {
        this.aClass = aClass;
        this.table = table;
        this.idField = new HashMap<>();
        this.columnFields = new HashMap<>();
    }

    // TODO: 8/17/2021 figure out parameters (ResultSet?)
    public E convertToObject()
    {
        return null;
    }

    // TODO: 8/17/2021 figure out return type (List of columns?)
    public E convertToDatabaseRecord(E object)
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
