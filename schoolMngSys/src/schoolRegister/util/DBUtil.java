package schoolRegister.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String driver="com.mysql.jdbc.Driver";
    private static final String dbURL="jdbc:mysql://localhost/SchoolDemo";
    private static final String user="root";
    private static final String password="Najah!@";
    private static Connection connection;
    //init
    public  static Connection setConnect() {


    try{
        //register the driver
        Class.forName(driver);
        //make connection
        connection= DriverManager.getConnection(dbURL,user,password);

    }
    catch(ClassNotFoundException e){
        e.printStackTrace();
    }
    catch (SQLException e){
        e.printStackTrace();
    }
        return connection;
    }



}
