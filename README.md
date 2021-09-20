# Project 1 ORM

## Project Description

A custom object relational mapping (ORM) framework, written in Java, which allows for a simplified and SQL-free interaction with a relational data source. Low-level JDBC is completely abstracted away from the developer, allowing them to easily query and persist data to a data source. Makes heavy use of the Java Reflection API in order to support any entity objects as defined by the developer. Additionally, the framework offers connection pooling to support multi-threaded applications.

## Technologies Used

* Java 8
* PostgreSQL
* Apache Maven
* AWS RDS

## Features

* All CRUD operations supported (Create, Read, Update, and Delete)
* All JDBC logic abstracted away. Communication with database done through ORM object methods.
* Configuration is done entirely through custom annotations. No XML or external config files required

To-do list:
* Table creation/deletion through ORM
* Custom get methods based on object attributes
* Remove need to annotate the index of a column in its constructor

## Getting Started
   
1. Download the Project1ORM-0.0.1-SNAPSHOT.jar file, which can be found in the root directory of this project.
2. Import it into your project. The simplest way to do so is to add the following to your pom.xml file.
```
<dependency>
    <groupId>com.revature.orm</groupId>
    <artifactId>Project1ORM</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>system</scope>
    <systemPath>${basedir}/lib/Project1ORM-0.0.1-SNAPSHOT.jar</systemPath>
</dependency>
```

## Usage

1. Create your entities.
1. Annotate your entity class/classes (the class that corresponds with your database table) with @Table(tablename = "insert table name here")
2. In your entity class
    - annotate the constructor that you want to use when creating objects from records in the database with @MetamodelConstructor
      - this constructor should contain, as parameters, all of and only the fields which have a corresponding column in the database table
    - annotate all of your column fields with @Column(columnname = {column name}, parameterNumber = {index this field appears in the metamodel constructor})
    - annotate your primary key field with @PrimaryKey(primaryKeyName = {primary key name})
![Screenshot (146)](https://user-images.githubusercontent.com/23224121/133950499-2047a83b-6a73-4ac3-87b9-46043e7585f4.png)
3. Create an ORM object and pass in your database information (url, username, password) as well as a list of all entity classes into the constructor
![Screenshot (147)](https://user-images.githubusercontent.com/23224121/133950621-7f5ab905-aa91-4465-af9f-0290c427cdb3.png)
4. To perform CRUD actions, call these methods with your ORM object
    - getRecords(String tablename) - retrieves all records from the corresponding table
    - addRecord(Object newRecord) - adds a new record into the table
    - updateRecord(Object updatedRecord) - updates a record from the table
    - deleteRecord(Object oldRecord) - deletes a record from the table

To see a demo project in action, go here - https://github.com/210726-Enterprise/Victor_Liu_project-1_webapp.
