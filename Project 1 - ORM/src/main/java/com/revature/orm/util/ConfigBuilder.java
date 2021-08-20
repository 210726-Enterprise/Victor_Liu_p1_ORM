package com.revature.orm.util;

import com.revature.orm.annotations.Table;
import com.revature.orm.model.Metamodel;

import java.util.ArrayList;
import java.util.List;

// TODO: 8/20/2021 rename class 
public class ConfigBuilder
{
    private static List<Metamodel> metamodels;
    private static ConnectionUtilities connectionUtilities;
    
    public static void configure()
    {
        connectionUtilities = new ConnectionUtilities();
        metamodels = new ArrayList<>();
    }
    
    public static void addRecord(Object newRecord)
    {
        connectionUtilities.create(newRecord, findMetamodel(newRecord));
    }
    
    public static void getRecords(String table)
    {
        connectionUtilities.read(findMetamodel(table));
    }
    
    public static void updateRecord(Object updatedRecord)
    {
        connectionUtilities.update(updatedRecord, findMetamodel(updatedRecord));
    }
    
    public static void deleteRecord(Object oldRecord)
    {
        connectionUtilities.delete(oldRecord, findMetamodel(oldRecord));
    }
    
    private static Metamodel<?> findMetamodel(Object relation)
    {
        for(Metamodel<?> metamodel : metamodels)
        {
            if(relation.getClass().equals(metamodel.getAClass()))
            {
                return metamodel;
            }
        }
        return null;
    }

    private static Metamodel<?> findMetamodel(String table)
    {
        for(Metamodel<?> metamodel : metamodels)
        {
            if(table.equals(metamodel.getAClass().getAnnotation(Table.class).tableName()))
            {
                return metamodel;
            }
        }
        return null;
    }
}
