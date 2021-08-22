package com.revature.orm.util;

import com.revature.orm.annotations.Table;
import com.revature.orm.model.Metamodel;

import java.util.ArrayList;
import java.util.List;

// TODO: 8/20/2021 rename class
public class ORM
{
    private List<Metamodel> metamodels;
    private ConnectionUtilities connectionUtilities;
    
    public ORM(String dbUrl, String username, String password, List<Class> classes)
    {
        connectionUtilities = new ConnectionUtilities();
        connectionUtilities.createConnection(dbUrl, username, password);
        metamodels = new ArrayList<>();
        for(Class clazz : classes)
        {
            metamodels.add(new Metamodel(clazz));
        }
    }
    
    public boolean addRecord(Object newRecord)
    {
        return connectionUtilities.create(newRecord, findMetamodel(newRecord));
    }
    
    public List<?> getRecords(String table)
    {
        return connectionUtilities.read(findMetamodel(table));
    }
    
    public boolean updateRecord(Object updatedRecord)
    {
        return connectionUtilities.update(updatedRecord, findMetamodel(updatedRecord));
    }
    
    public boolean deleteRecord(Object oldRecord)
    {
        return connectionUtilities.delete(oldRecord, findMetamodel(oldRecord));
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
