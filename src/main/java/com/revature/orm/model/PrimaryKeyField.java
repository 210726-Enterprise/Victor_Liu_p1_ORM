package com.revature.orm.model;

import com.revature.orm.annotations.PrimaryKey;

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
            throw new IllegalStateException("Provided field, " + field.getName() + "is not annotated with @PrimaryKey");
        }
        this.fieldName = field.getName();
        this.columnName = annotation.primaryKeyName();
        this.type = field.getType();
    }

    public String getClassFieldName()
    {
        return fieldName;
    }

    public String getTableColumnName()
    {
        return columnName;
    }

    public Class<?> getType()
    {
        return type;
    }
}
