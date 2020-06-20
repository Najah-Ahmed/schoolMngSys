package schoolRegister.controllers;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import schoolRegister.dao.StudentDAO;
import schoolRegister.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class DashboardController implements Initializable {


    ArrayList<Student> students = new ArrayList<>();
    StudentDAO studentDAO=new StudentDAO();




    @FXML
    private Label labelStdntCounter;

    @FXML
    private Label labelTeacherCounter;

    @FXML
    private Label labelClassCounter;

    @FXML
    private Label labelStudjectCounter;

    @FXML
    private TableView<Student> tbViewStudent;

    @FXML
    private TableColumn<Student,String > studentName;

    @FXML
    private TableColumn<Student ,Integer> studentId;

    @FXML
    private TableColumn<Student,String > studentClass;

    @FXML
    private TableColumn<Student,String> studentLevel;


    @FXML
    private Button refersh;

    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtSearch;



    @FXML
    private PieChart chartView;

    @FXML
    private Button btnAddNew;

    public DashboardController() throws SQLException, ClassNotFoundException {
    }


    @FXML
    void addNew(ActionEvent event) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/schoolRegister/app/newStudent.fxml"));
            Stage addNew =new Stage();
            addNew.setTitle("Add New Student");
            addNew.getIcons().add(new Image("/schoolRegister/images/icon.png"));
            addNew.setScene(new Scene(root ,900,600));
            addNew.show();

        }
        catch (IOException ce ){
            System.err.println(ce.getCause());
        }

    }


    @FXML
    void onDeleting(ActionEvent event) throws SQLException {
        try{
            Student student =tbViewStudent.getSelectionModel().getSelectedItem();
            int i=studentDAO.deleteStudent(student);
            if (i>0){
                Alert alert =new Alert(Alert.AlertType.INFORMATION,i+"of rows affected ");
                alert.show();
                students =studentDAO.readStudents();
                tbViewStudent.setItems(FXCollections.observableList(students));
                chartView.getData().clear();
                loadChart(students);
                labelStdntCounter.setText(String.valueOf(students.size()));

            }
        }
        catch (NullPointerException ne){
            Alert alert =new Alert(Alert.AlertType.ERROR,"Please Select Row to Delete");
        }

    }

    @FXML
    void refesh(ActionEvent event) throws SQLException {

        students=studentDAO.readStudents();
        tbViewStudent.setItems(FXCollections.observableList(students));
        chartView.getData().clear();
        loadChart(students);
        labelStdntCounter.setText(String.valueOf(students.size()));

    }

    @FXML
    void searching(KeyEvent event) {
        FilteredList<Student> flStudents =new FilteredList<>(FXCollections.observableList(students),p->true);
        String searchTerm=txtSearch.getText().toString().toLowerCase();
        flStudents.setPredicate(p->p.getName().toLowerCase().contains(searchTerm));
        tbViewStudent.setItems(flStudents);



    }

    @FXML
    void editClass(TableColumn.CellEditEvent event) throws SQLException {
        Student editedStudent=tbViewStudent.getSelectionModel().getSelectedItem();
        editedStudent.setClassName(event.getNewValue().toString());
        ArrayList<Student> students=studentDAO.readStudents();
        int r =studentDAO.updateStudent(editedStudent);
        tbViewStudent.setItems(FXCollections.observableList(students));
        if(r>0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION,r+"of rows effected");
            alert.show();
        }


    }

    @FXML
    void editID(TableColumn.CellEditEvent event) throws SQLException {
        Student editedStudent= tbViewStudent.getSelectionModel().getSelectedItem();
        editedStudent.setId(Integer.parseInt(event.getNewValue().toString()));
        int r=studentDAO.updateStudent(editedStudent);
        if(r>0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION,r+"of rows effected");
        }
    }

    @FXML
    void editLevel(TableColumn.CellEditEvent event) throws SQLException {
        //tbViewStudent.getSelectionModel().getSelectedItem().setLevel(event.getNewValue().toString());
        Student editedStudent=tbViewStudent.getSelectionModel().getSelectedItem();
        editedStudent.setLevel(event.getNewValue().toString());
        int r=studentDAO.updateStudent(editedStudent);
        if(r>0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION,r+"of rows effected");
        }
    }

    @FXML
    void editName(TableColumn.CellEditEvent event) throws SQLException {
        Student  editedStudent=tbViewStudent.getSelectionModel().getSelectedItem();
        editedStudent.setName(event.getNewValue().toString());

        int r=studentDAO.updateStudent(editedStudent);
        if(r>0){
            Alert alert =new Alert(Alert.AlertType.INFORMATION,r+"of rows effected");
        }
    }




    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        chartView.setLabelsVisible(false);
        try{
            students =studentDAO.readStudents();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

//        Student student = new Student("marwa Ahmed.Yasen ", 8788, "KG2", "Class A");
//        students.add(student);
//        student = new Student("mawada Ahmed.Yasen ", 8787, "gradeOne", "Class A");
//        students.add(student);
//        student = new Student("Omer Ahmed.Yasen ", 8789, "KG1", "Class A");
//        students.add(student);
//        student = new Student("Hussien Ahmed Saed ", 8790, "KG3", "Class A");
//        students.add(student);


        studentName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        studentLevel.setCellValueFactory(new PropertyValueFactory<Student, String>("level"));
        studentClass.setCellValueFactory(new PropertyValueFactory<Student, String>("className"));
        studentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));


        tbViewStudent.setItems(FXCollections.observableList(students));
        labelStdntCounter.setText(String.valueOf(students.size()));
        loadChart(students);
        tbViewStudent.setEditable(true);


        studentName.setCellFactory(TextFieldTableCell.forTableColumn());
        studentId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        studentLevel.setCellFactory(TextFieldTableCell.forTableColumn());
        studentClass.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    private  void loadChart(ArrayList<Student> students){
        int KG1=0, KG2=0,KG3=0 ,gradeOne=0;
        for (Student student:students){
            switch (student.getLevel()){
                case "KG1":
                    KG1++;
                    break;
                case "KG2":
                    KG2++;
                    break;
                case "KG3":
                    KG3++;
                    break;
                case "gradeOne":
                    gradeOne++;



            }
        }


        chartView.getData().add(new PieChart.Data("KG1",KG1));
        chartView.getData().add(new PieChart.Data("KG2",KG2));
        chartView.getData().add(new PieChart.Data("KG3",KG3));
        chartView.getData().add(new PieChart.Data("gradeOne",gradeOne));
    }
}
















