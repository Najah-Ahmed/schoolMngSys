package inventorys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String dbURL="jdbc:mysql://localhost/SchoolDemo";
    private static final String user="root";
    private static final String password="Najah!@";
    private static Connection connection;

    public static Connection setConnect()  {
        try{
            Class.forName(driver);
            connection= DriverManager.getConnection(dbURL,user,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch ( SQLException e){
            e.printStackTrace();

        }
        return connection;
    }

}
