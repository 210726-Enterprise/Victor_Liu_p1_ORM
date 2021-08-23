package com.revature.orm.util;

import com.revature.orm.annotations.Table;
import com.revature.orm.model.Metamodel;

import java.util.ArrayList;
import java.util.List;

// TODO: 8/20/2021 rename class
public class ORM
{
    private List<Metamodel> metamodels;
    private DMLMethods databaseManipulator;
    
    public ORM(String dbUrl, String username, String password, List<Class> classes)
    {
        ConnectionUtilities.createConnection(dbUrl, username, password);
        metamodels = new ArrayList<>();
        for(Class clazz : classes)
        {
            metamodels.add(new Metamodel(clazz));
        }
        databaseManipulator = new DMLMethods();
    }
    
    public boolean addRecord(Object newRecord)
    {
        return databaseManipulator.create(newRecord, findMetamodel(newRecord));
    }
    
    public List getRecords(String table)
    {
        return databaseManipulator.read(findMetamodel(table));
    }
    
    public boolean updateRecord(Object updatedRecord)
    {
        return databaseManipulator.update(updatedRecord, findMetamodel(updatedRecord));
    }
    
    public boolean deleteRecord(Object oldRecord)
    {
        return databaseManipulator.delete(oldRecord, findMetamodel(oldRecord));
    }
    
    private Metamodel<?> findMetamodel(Object relation)
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

    private Metamodel<?> findMetamodel(String table)
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
