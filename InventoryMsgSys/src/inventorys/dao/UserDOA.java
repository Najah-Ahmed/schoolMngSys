package inventorys.dao;

import java.sql.*;


import inventorys.models.User;
import inventorys.utils.DButil;
import javafx.scene.control.Alert;

public class UserDOA {
    private Connection connection;
    public UserDOA(){
        connection=DButil.setConnect();
    }
public User getUser(String username, String password) throws SQLException {
    String sql ="SELECT * FROM Users WHERE  username=? AND password=?";
    PreparedStatement ps=connection.prepareStatement(sql);
    ps.setString( 1,username);
    ps.setString(2,password);
    ResultSet rs =ps.executeQuery();
    User user =new User(username,password);
    while(rs.next()){
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getInt("id"));
    }


        return user;
}
    public void   addItem(User user) throws SQLException {
        String sql = "INSERT INTO Items(username,password)VALUES("+user.getUsername() + ", '"+user.getUsername() +"' )";
        //Create statement
        Statement statement =connection.createStatement();
        //execute the statement
        int r=statement.executeUpdate(sql);
        if (r>0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION, r+ "Inserted new User");
            alert.show();
        }
    }

}
