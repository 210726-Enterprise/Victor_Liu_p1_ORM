# Victor_Liu_p1_ORM

A custom ORM developed in Java 8



## Setup instructions:

1. Import this package into your project
2. Annotate your entity class (the class that corresponds with your database table) with @Table(tablename = "insert table name here")
3. In your entity class
    - annotate the constructor that you want to use when creating objects from records in the database with @MetamodelConstructor
      - this constructor should contain all of and only the fields which have a corresponding column in the database table
    - annotate all of your column fields with @Column(columnname = "insert column name here", parameterNumber = "insert index this field appears in the metamodel constructor here")
    - annotate your primary key field with @PrimaryKey(primaryKeyName = "insert primary key name here")
4. Create an ORM object and pass in your database information (url, username, password) and a list of all entity classes into the constructor
5. When you want to perform CRUD actions, call these methods with your ORM object
    - getRecords(String tablename) - retrieves all records from the corresponding table
    - addRecord(Object newRecord) - adds a new record into the table
    - updateRecord(Object updatedRecord) - updates a record from the table
    - deleteRecord(Object oldRecord) - deletes a record from the table

https://github.com/210726-Enterprise/Victor_Liu_project-1_webapp - my custom webapp which uses this ORM
