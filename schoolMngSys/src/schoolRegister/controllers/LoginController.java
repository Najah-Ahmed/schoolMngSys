package schoolRegister.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import schoolRegister.dao.UserDAO;
import schoolRegister.models.User;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    UserDAO userDAO=new UserDAO();

    @FXML
    private PasswordField pwd;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField username;

    @FXML
    private Hyperlink forgetpwd;

    @FXML
    private Hyperlink btnSignup;
    @FXML
    private Label alertDanger;



    @FXML
    void Forgetpwd(ActionEvent event) {

    }

    @FXML
    void Login(ActionEvent event) throws SQLException {
//    System.out.println("login");
        User user= userDAO.getUser(username.getText().toString(),pwd.getText().toString());

    if (user.getUsername() != null ){

     try{
         Stage dashboardStage=new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("/schoolRegister/app/dashboard.fxml"));
         dashboardStage.getIcons().add(new Image("/schoolRegister/images/9580547601553228570-128.png"));
         dashboardStage.setTitle("Dashboard");
         Scene scene =new Scene(root,900,600);
         dashboardStage.setScene(scene);
         //to close so hel one of contollers
         username.getScene().getWindow().hide();
         dashboardStage.show();
     } catch (IOException e) {
         e.printStackTrace();
     }
    }
    else{
       alertDanger.setVisible(true);
    }

    }
  
    @FXML
    void Signup(ActionEvent event) {

    }

}
