package schoolRegister.dao;
 import schoolRegister.models.Student;
 import schoolRegister.util.DBUtil;
 import javafx.scene.control.Alert;


 import java.sql.*;
 import java.util.ArrayList;


public class StudentDAO {
 private static Connection connection;
 private Statement stm;
 public StudentDAO() {
  connection=DBUtil.setConnect();
 }
 public  void createStudent (Student st) throws SQLException{
  //create the statement
  String sql = "INSERT INTO Students(id, name, level, section, gender, languages)VALUES("+st.getId() + ", '"+st.getName() +"' ,'"+st.getLevel()
          +"', '"+st.getClassName() + "', '" + st.getGender() + "', '" + (st.getLanguage()[0] != null? st.getLanguage()[0] :null)+ ","+(st.getLanguage()[1] != null? st.getLanguage()[1] :null)+"')";
//Create statement
  Statement statement =connection.createStatement();
  //execute the statement
  int r=statement.executeUpdate(sql);
  if (r>0){
   Alert alert =new Alert(Alert.AlertType.INFORMATION, r+ "Inserted new student");
   alert.show();
  }


 }

 public ArrayList<Student> readStudents() throws SQLException {
  ArrayList<Student> students=new ArrayList<>();
  String sql="SELECT * FROM students";
 stm = connection.createStatement();


  ResultSet rs=stm.executeQuery(sql);
  while (rs.next()){
   Student student =convertToStudent(rs);
   students.add(student);
  }
  return students;
 }
 public int deleteStudent(Student student) throws SQLException {
  String sql="Delete from Students where id=?";
  PreparedStatement ps=connection.prepareStatement(sql);
  ps.setInt(1,student.getId());
  int r= ps.executeUpdate();
  return r;

 }

 public int updateStudent(Student student) throws SQLException {
  String sql ="UPDATE Students set name=?, id =? , level=?,section=? where id=?";
  PreparedStatement ps=connection.prepareStatement(sql);
  ps.setInt(2,student.getId());
  ps.setString(1,student.getName());
  ps.setString(3,student.getLevel());
  ps.setString(4,student.getClassName());
  ps.setInt(5,student.getId());

  int r=ps.executeUpdate();
   return r;


 }


 private Student convertToStudent(ResultSet rs) throws SQLException {
  Student student=new Student();
  student.setId(rs.getInt("id"));
  student.setName(rs.getString("name"));
  student.setClassName(rs.getString("section"));
  student.setLevel(rs.getString("level"));
  return student;


 }
}
