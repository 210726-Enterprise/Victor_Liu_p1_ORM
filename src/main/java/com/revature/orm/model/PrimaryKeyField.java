package com.revature.orm.model;

import com.revature.orm.annotations.PrimaryKey;

import java.lang.reflect.Field;

/**\
 * helper class for linking class fields and table primary keys
 */
public class PrimaryKeyField
{
    private final Field field;
    private final String fieldName;
    private final String columnName;
    private final Class<?> type;

    public PrimaryKeyField(Field field)
    {
        PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
        if (annotation == null)
        {
            throw new IllegalStateException("Provided field, " + field.getName() + "is not annotated with @PrimaryKey");
        }
        this.field = field;
        this.fieldName = field.getName();
        this.columnName = annotation.primaryKeyName();
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
}
