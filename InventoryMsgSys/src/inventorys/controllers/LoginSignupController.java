package inventorys.controllers;

import inventorys.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import inventorys.dao.UserDOA;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginSignupController implements Initializable {
            UserDOA userDOA=new UserDOA();






    @FXML
    private TextField userSignup;

    @FXML
    private PasswordField pwdSignup;

    @FXML
    private ChoiceBox<?> listUser;

    @FXML
    private Button signup;

    @FXML
    private Button login;

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField pwdLogin;

    @FXML
    void onLogin(ActionEvent event) throws SQLException {
        User user=userDOA.getUser(userLogin.getText().toString(),pwdLogin.getText().toString());

        if (user.getUsername() !=null){

            try{
                Stage dashboardStage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/Inventorys/app/Dashboard.fxml"));
                dashboardStage.getIcons().add(new Image("/Inventorys/images/boxes.png"));
                dashboardStage.setTitle("Dashboard");
                Scene scene =new Scene(root,900,600);
                dashboardStage.setScene(scene);

                userLogin.getScene().getWindow().hide();
                dashboardStage.show();

            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onSignup(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
