package inventorys.dao;

import inventorys.models.Items;
import inventorys.utils.DButil;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class ItemDOA {
    private static Connection connection;
    private Statement stm;
    public ItemDOA(){
        connection= DButil.setConnect();
    }

    public void   addItem(Items item) throws SQLException {
        String sql = "INSERT INTO Items(trackId, productName, quantity,storedSection,expiredDate, importDate)VALUES("+item.getTrackId() + ", '"+item.getProductName() +"' ," +
                "'"+item.getQuantity() +"', '"+item.getDateOfExpired()+ "', '" + item.getImportDate() + "')";
        //Create statement
        Statement statement =connection.createStatement();
        //execute the statement
        int r=statement.executeUpdate(sql);
        if (r>0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION, r+ "Inserted new Item");
            alert.show();
        }
    }


    public ArrayList<Items> readItems() throws SQLException {
        ArrayList<Items> items=new ArrayList<>();
        String sql="SELECT * FROM Items";
        stm = connection.createStatement();


        ResultSet rs=stm.executeQuery(sql);
        while (rs.next()){
            Items item =convertToItems(rs);
            items.add(item);
        }
        return items;
    }
    public int deleteStudent(Items item) throws SQLException {
        String sql="Delete from Items where trackID=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(1,item.getTrackId());
        int r= ps.executeUpdate();
        return r;

    }

    public int updateStudent(Items item) throws SQLException {
        String sql ="UPDATE Items set productName=?,trackId=?,  quantity=?,storedSection=?,expiredDate=?, importDate=? where id=?";
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setInt(2,item.getTrackId());
        ps.setString(1,item.getProductName());
        ps.setFloat(3,item.getQuantity());
        ps.setString(4,item.getStoredSection());
        ps.setString(5,item.getDateOfExpired());
        ps.setString(6,item.getImportDate());

        int r=ps.executeUpdate();
        return r;


    }


    private Items convertToItems(ResultSet rs) throws SQLException {
        Items item=new Items();


        item.setTrackId(rs.getInt("trackId"));
        item.setProductName(rs.getString("productName"));
        item.setQuantity(rs.getInt("quantity"));
        item.setStoredSection(rs.getString("storedSection"));
        item.setDateOfExpired(rs.getString("DateOfExpired"));
        item.setImportDate(rs.getString("ImportDate"));

        return item;


    }
}
