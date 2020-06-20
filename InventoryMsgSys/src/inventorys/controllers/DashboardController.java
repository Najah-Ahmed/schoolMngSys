package inventorys.controllers;



import inventorys.models.Items;
import inventorys.dao.ItemDOA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    ArrayList<Items> students = new ArrayList<>();
    ItemDOA itemDOA=new ItemDOA();
    @FXML
    private TreeTableView<Items> tbview;

    @FXML
    private TreeTableColumn<Items ,Integer> track_id;

    @FXML
    private TreeTableColumn<Items ,String> productName;

    @FXML
    private TreeTableColumn<Items ,Integer> quantityCount;

    @FXML
    private TreeTableColumn<Items ,String> stroredSection;

    @FXML
    private TreeTableColumn<Items ,Float> expiredDate;

    @FXML
    private TreeTableColumn<Items ,Float> importDate;

    @FXML
    private TextField searchField;

    @FXML
    private Button refreshBtn;

    @FXML
    private Button delBtn;

    @FXML
    private Button addBtn;

    public DashboardController() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void addNew(ActionEvent event) {
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/Inventorys/app/addItems.fxml"));

            Stage addNew =new Stage();
            addNew.setTitle("Add New Items");
            addNew.getIcons().add(new Image("/Inventorys/images/boxes.png"));
            addNew.setScene(new Scene(root ,900,600));
            addNew.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onDeleting(ActionEvent event) {
        try{
            Items item=tbview.getSelectionModel().getSelectedItem();
            int i=itemDOA.deleteItems(item);

        }

    }

    @FXML
    void onRefersh(ActionEvent event) {

    }

    @FXML
    void searching(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
