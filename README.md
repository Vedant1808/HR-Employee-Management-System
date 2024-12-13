# HR Management System  

This project is a desktop-based HR Management System with modules for **Designation** and **Employee**, developed using the **Java programming language**.  

## Project Architecture  
The application is built with a **three-layer architecture**, ensuring modularity and flexibility:  
1. **Data Layer**:  
   - Used both file storage and a MySQL database to store data.  
   - Connected Java to MySQL using **JDBC**.  
2. **Business Layer**:  
   - Managed various data structures to enable efficient data access and operations.  
3. **Presentation Layer**:  
   - Built a desktop application interface using **Java Swing API**.  

## Key Feature  
The project is designed with a **modular architecture**, allowing any layer (data, business, or presentation) to be changed independently without affecting the others.  

## Execution Instructions  
To run the application, use the following command:
C:\JavaProjects\hr\pl>
```bash
java -classpath ..\common\dist\hrcommon.jar;..\dbdl\build\libs\dbdl.jar;..\bl\build\libs\bl.jar;build\libs\pl.jar;c:\java\jarFile\itext7*;c:\java\jarFile\mysqljar*;. com.thinking.machines.hr.pl.Main
```

## Tools and Technologies Used  
- **Programming Language**: Java  
- **Database**: MySQL  
- **Connection**: JDBC  
- **Frontend**: Java Swing API
- **PDF** : IText PDF  

 
