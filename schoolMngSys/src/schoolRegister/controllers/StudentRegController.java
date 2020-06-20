package schoolRegister.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import schoolRegister.dao.StudentDAO;
import schoolRegister.models.Student;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentRegController  implements Initializable {
 StudentDAO studentDAO=new StudentDAO();
    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtID;

    @FXML
    private ComboBox<String> cbLevel;

    @FXML
    private ComboBox<String> cbClass;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private CheckBox cbArabic;

    @FXML
    private CheckBox cbEnglish;

    @FXML
    private Button btnRegister;

    @FXML
    private Label wrongCredentials;


    @FXML
    void Register(ActionEvent even){
        try {
            String name=txtFullName.getText().toString();
            int id =Integer.parseInt(txtID.getText().toString());
            String level = cbLevel.getSelectionModel().getSelectedItem().toString();
            String className = cbClass.getSelectionModel().getSelectedItem().toString();
            Student st =new Student(name,id,level,className);
            String gender =this.gender.getSelectedToggle().getUserData().toString();
            st.setGender(gender);
            String [] languages =new String[2];
            if(cbArabic.isSelected()){
                languages[0]="Arabic";
            }
            if (cbEnglish.isSelected()){
                languages[1]="English";
            }
            st.setLanguage(languages);
            System.out.println(st);
            studentDAO.createStudent(st);

        }
        catch (NullPointerException ne){
            System.err.println(ne.getStackTrace());
        }
        catch (NumberFormatException nf){
            System.err.println(nf.getStackTrace());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

     cbClass.getItems().addAll("Class A", "Class B","Class C","Class D");
     cbLevel.getItems().addAll("KG1", "KG2","KG3","Grade One");
     rbFemale.setUserData("Female");
     rbMale.setUserData("Male");
    }

}
