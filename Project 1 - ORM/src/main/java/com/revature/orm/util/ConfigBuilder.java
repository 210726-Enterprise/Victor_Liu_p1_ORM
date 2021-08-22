package com.revature.orm.util;

import com.revature.orm.annotations.Table;
import com.revature.orm.model.Metamodel;

import java.util.ArrayList;
import java.util.List;

// TODO: 8/20/2021 rename class
public class ConfigBuilder
{
    private List<Metamodel> metamodels;
    private ConnectionUtilities connectionUtilities;
    
    public void ConfigBuilder(String dbUrl, String username, String password, List<Class> classes)
    {
        connectionUtilities = new ConnectionUtilities();
        connectionUtilities.createConnection(dbUrl, username, password);
        metamodels = new ArrayList<>();
        for(Class clazz : classes)
        {
            metamodels.add(new Metamodel(clazz));
        }
    }
    
    public void addRecord(Object newRecord)
    {
        connectionUtilities.create(newRecord, findMetamodel(newRecord));
    }
    
    public void getRecords(String table)
    {
        connectionUtilities.read(findMetamodel(table));
    }
    
    public void updateRecord(Object updatedRecord)
    {
        connectionUtilities.update(updatedRecord, findMetamodel(updatedRecord));
    }
    
    public void deleteRecord(Object oldRecord)
    {
        connectionUtilities.delete(oldRecord, findMetamodel(oldRecord));
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
