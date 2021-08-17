package com.revature.util;

import java.lang.reflect.Field;
import java.util.*;

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
