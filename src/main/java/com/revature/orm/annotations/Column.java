package com.revature.orm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation for designating column fields
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column
{
    /**
     * @return name of matching column in the database
     */
    String columnName();

    /**
     * @return order it's inserted in Metamodel constructor (0 = left-most parameter)
     */
    int parameterNumber();
}
