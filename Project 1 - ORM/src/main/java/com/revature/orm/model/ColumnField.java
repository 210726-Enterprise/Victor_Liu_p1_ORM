package com.revature.orm.model;

import com.revature.orm.annotations.Column;

import java.lang.reflect.Field;

public class ColumnField
{
    private final Field field;
    private final String fieldName;
    private final String columnName;
    private final Class<?> type;

    public ColumnField(Field field)
    {
        Column annotation = field.getAnnotation(Column.class);
        if (annotation == null)
        {
            throw new IllegalStateException("Provided field, " + field.getName() + "is not annotated with @Column");
        }
        this.field = field;
        this.fieldName = field.getName();
        this.columnName = annotation.columnName();
        this.type = field.getType();
    }

    public Field getField()
    {
        return field;
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

    public int compareTo(ColumnField other)
    {
        Column thisAnnotation = field.getAnnotation(Column.class);
        Column otherAnnotation = other.getField().getAnnotation(Column.class);
        return thisAnnotation.parameterNumber() - otherAnnotation.parameterNumber();
    }
}
