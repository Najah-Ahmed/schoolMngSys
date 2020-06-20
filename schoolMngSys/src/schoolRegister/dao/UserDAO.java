package schoolRegister.dao;

import schoolRegister.models.User;
import schoolRegister.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;
    public UserDAO()  {
        connection= DBUtil.setConnect();
    }
    public User getUser(String username,String password) throws SQLException {
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
}
