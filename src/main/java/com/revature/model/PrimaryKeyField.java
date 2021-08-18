package com.revature.model;

import com.revature.annotations.PrimaryKey;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class PrimaryKeyField
{
    private String fieldName;
    private String columnName;
    private Class type;

    public PrimaryKeyField(Field field)
    {
        PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
        if (annotation == null)
        {
            throw new IllegalStateException("Provided field, " + getName() + "is not annotated with @PrimaryKey");
        }
        this.fieldName = field.getName();
        this.columnName = annotation.primaryKeyName();
        this.type = field.getType();
    }

    public String getName()
    {
        return fieldName;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public Class<?> getType()
    {
        return type;
    }
}
