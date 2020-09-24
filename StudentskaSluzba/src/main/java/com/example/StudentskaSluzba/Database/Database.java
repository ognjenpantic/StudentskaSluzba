package com.example.StudentskaSluzba.Database;
import com.example.StudentskaSluzba.Models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

   private Connection conn =  null;
   public Statement stmt = null;

   public Database(){
      try {
         System.out.println("Processing...");
         conn = DriverManager.getConnection("jdbc:mariadb://localhost/studies","root","");
         stmt = conn.createStatement();
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }


   public void addStudentToDB(Student student){

      String inc = "ALTER TABLE `students` AUTO_INCREMENT=1";
      String sql="INSERT INTO `students` (`FirstName`, `LastName`, `IndexNumber`, `EnrollmentYear`,`Grade`) " +
              "VALUES ('" + student.getFirst() + "', " +
              "'" + student.getLast() + "', " +
              "'" + student.getIndex() + "', " +
              "'" + student.getYear() + "', " +
              "'" + student.getGrade()+ "')";

      try {
         System.out.println("Processing...");
         stmt.execute(inc);
         stmt.executeUpdate(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }
   public void addProfToDB(Professor professor) {
      String inc="ALTER TABLE `professors` AUTO_INCREMENT=1";
      String sql=" INSERT INTO `professors`(`FirstName`, `LastName`)" +
              "VALUES ('" + professor.getFirst() + "', " +
              "'" + professor.getLast() + "')";

      try {
         System.out.println("Processing...");
         stmt.execute(inc);
         stmt.executeUpdate(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }
   public void addSubToDB(Subject subject){
      String inc="ALTER TABLE `subjects` AUTO_INCREMENT=1";
      String sql="INSERT INTO `subjects`(`SubjectID`, `SubjectName`, `ECTS`) " +
              "VALUES ('" + subject.getSubjectID() + "', " +
              "'" + subject.getSubjectName() + "', " +
              "'" + subject.getECTS() +"')";
      try {
         System.out.println("Processing...");
         stmt.execute(inc);
         stmt.executeUpdate(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }
   public void addProfsubtoDB(Prof_sub assignedSub){
      String inc="ALTER TABLE `prof_sub` AUTO_INCREMENT=1";
      String sql="INSERT INTO `prof_sub`(`ProfessorID`, `SubjectID`) " +
              "VALUES ('" + assignedSub.getProfessorID() + "', " +
              "'" + assignedSub.getSubjectID() + "')";
      try {
         System.out.println("Processing...");
         stmt.execute(inc);
         stmt.executeUpdate(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }

   public void addReg(int StudentID, String SubjectID){
      String inc="ALTER TABLE `selected_subs` AUTO_INCREMENT=1";
      String sql="INSERT INTO `selected_subs`(`StudentID`, `SubjectID`) " +
              "VALUES ('" + StudentID + "', " +
              "'" + SubjectID + "')";
      try {
         System.out.println("Inserting..");
         stmt.execute(inc);
         stmt.executeUpdate(sql);
         System.out.println("Inserted.");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }

   public void delReg(int StudentID, String SubjectID){
      String sql = "DELETE FROM `selected_subs` WHERE `selected_subs`.`StudentID` ='" + StudentID  +
              "' AND `selected_subs`.`SubjectID` = '" + SubjectID +"'";

      try {
         System.out.println("Processing...");
         stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }
   public void addMark(int StudentID,String SubjectID, String SubjectName, int Mark, int ECTS){
      String inc="ALTER TABLE `passedexmas` AUTO_INCREMENT=1";
      String sql="INSERT INTO `passedexmas`(`StudentID`, `SubjectID`, `SubjectName`, `Mark`,`ECTS`) " +
              "VALUES ('" + StudentID + "','" + SubjectID + "','" + SubjectName + "','" + Mark + "','" + ECTS + "')";
      try {
         System.out.println("Processing...");
         stmt.execute(inc);
         stmt.executeUpdate(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }

   public List<Professor> getListOfProf() throws SQLException{
      String sql="SELECT * FROM `professors`  ORDER BY `professors`.`ID` ASC";
      ResultSet rs=null;

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }

      List<Professor> list = new ArrayList<Professor>();

      while( rs.next())
      {
         Professor professor = new Professor(rs.getInt("ID"),
                 rs.getString("FirstName"),
                 rs.getString("LastName"));

         list.add(professor);
      }

      return list;
   }

   public List<Student> getListOfStudents() throws SQLException{
      String sql="SELECT * FROM `students`";
      ResultSet rs=null;

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }

      List<Student> list = new ArrayList<Student>();

      while( rs.next())
      {
         Student student = new Student(rs.getInt("ID"),
                 rs.getString("FirstName"),
                 rs.getString("LastName"),
                 rs.getInt("IndexNumber"),
                 rs.getInt("EnrollmentYear"),
                 rs.getInt("Grade"));

         list.add(student);
      }

      return list;
   }

   public List<Subject> getListOfSubs() throws SQLException{
      String sql="SELECT * FROM `subjects` ORDER BY `subjects`.`ID` ASC";
      ResultSet rs=null;

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }

      List<Subject> list = new ArrayList<Subject>();

      while( rs.next())
      {
         Subject subject = new Subject(rs.getInt("ID"),
                 rs.getString("SubjectID"),
                 rs.getString("SubjectName"),
                 rs.getInt("ECTS"));

         list.add(subject);
      }

      return list;
   }

   public List<Subject> getProf_sub(int ID) throws SQLException {
      ResultSet rs = null;
      String sql= ("SELECT * FROM `subjects` `s1` " +
              "WHERE `s1`.`SubjectID` IN " +
              "(SELECT `s2`.`SubjectID` FROM `prof_sub` `s2` " +
              "WHERE `s2`.`ProfessorID` = " + ID + ")");

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }


      List<Subject> list = new ArrayList<Subject>();
      while (rs.next()) {
         Subject subject = new Subject(rs.getInt("ID"),
                 rs.getString("SubjectID"),
                 rs.getString("SubjectName"),
                 rs.getInt("ECTS"));

         list.add(subject);
      }
      return list;
   }

   public List<Subject> getFreeSubjects(int ID) throws SQLException {
      ResultSet rs = null;
      String sql= "SELECT * FROM `subjects` `s1` " +
              "WHERE `s1`.`SubjectID` NOT IN " +
              "(SELECT `s2`.`SubjectID` FROM `prof_sub` `s2` )";

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }

      List<Subject> list = new ArrayList<Subject>();
      while (rs.next()) {
         Subject subject = new Subject(rs.getInt("ID"),
                 rs.getString("SubjectID"),
                 rs.getString("SubjectName"),
                 rs.getInt("ECTS"));

         list.add(subject);
      }
      return list;
   }

   public List<Exam_registration> getApplicationList(int ID) throws SQLException {
      ResultSet rs = null;
      String sql = ("SELECT `s`.`ID`, `s`.`IndexNumber`, `s`.`EnrollmentYear`, `sub1`.`SubjectID`, `sub`.`SubjectName`, `sub`.`ECTS` " +
              "FROM `selected_subs` `sub1`" +
              "LEFT JOIN `students` `s`" +
              "ON `sub1`.`StudentID` = `s`.`ID`" +
              "LEFT JOIN `subjects` `sub`" +
              "ON `sub1`.`SubjectID` = `sub`.`SubjectID`" +
              "WHERE `sub1`.`SubjectID` IN " +
              "(SELECT `ps`.`SubjectID` FROM `prof_sub` `ps` WHERE `ps`.`ProfessorID` = "+ID+") " +
              "AND `sub1`.`SubjectID` NOT IN " +
              "(SELECT `pe`.`SubjectID` FROM `passedexmas` `pe` " +
              "WHERE `sub1`.`StudentID` = `pe`.`StudentID`)");

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }


      List<Exam_registration> list = new ArrayList<Exam_registration>();
      while (rs.next()) {
         Exam_registration reg = new Exam_registration(rs.getInt("ID"),
                 rs.getString("SubjectID"),
                 rs.getString("SubjectName"),
                 rs.getInt("IndexNumber"),
                 rs.getInt("EnrollmentYear"),
                 rs.getInt("ECTS"));
         list.add(reg);
      }
      return list;
   }


   public List<Subject> getListOfSubs(String sql, int ID) throws SQLException {
      ResultSet rs=null;
      String sql2="SELECT * FROM `subjects` `s1` " +
              "WHERE `s1`.`SubjectID` "+sql+" " +
              "(SELECT `s2`.`SubjectID` FROM `selected_subs` `s2` " +
              "WHERE `s2`.`StudentID` = "+ ID +") AND `s1`.`SubjectID` NOT IN " +
              "(SELECT `s3`.`SubjectID` FROM `passedexmas` `s3` " +
              "WHERE `s3`.`StudentID` = "+ ID +")";
      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql2);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }


      List<Subject> list = new ArrayList<Subject>();
      while( rs.next())
      {
         Subject subject = new Subject(rs.getInt("ID"),
                 rs.getString("SubjectID"),
                 rs.getString("SubjectName"),
                 rs.getInt("ECTS"));

         list.add(subject);
      }

      return list;
   }

   public List<Passed_exams>  getListOfPassedX(int ID) throws SQLException {
      ResultSet rs =null;
      String sql="SELECT * FROM `passedexmas` " +
              "WHERE `passedexmas`.`StudentID` = "+ ID;
      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }


      List<Passed_exams> list = new ArrayList<Passed_exams>();
      while( rs.next())
      {
         Passed_exams exam = new Passed_exams();
         exam.setSubjectID(rs.getString("SubjectID"));
         exam.setSubjectName(rs.getString("SubjectName"));
         exam.setMark(rs.getInt("Mark"));
         exam.setECTS(rs.getInt("ECTS"));

         list.add(exam);
      }

      return list;
   }
   public List<Subject>  getListOfRegs(int ID) throws SQLException {
      ResultSet rs=null;
      String sql="SELECT * FROM `subjects` `s1` " +
              "WHERE `s1`.`SubjectID` IN " +
              "(SELECT `s2`.`SubjectID` FROM `selected_subs` `s2` " +
              "WHERE `s2`.`StudentID` = "+ ID +") AND `s1`.`SubjectID` NOT IN " +
              "(SELECT `s3`.`SubjectID` FROM `passedexmas` `s3` " +
              "WHERE `s3`.`StudentID` = "+ ID +")";

      try {
         System.out.println("Processing...");
         rs = stmt.executeQuery(sql);
         System.out.println("Done!");
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }

      List<Subject> list = new ArrayList<Subject>();
      while( rs.next())
      {
         Subject subject = new Subject(rs.getInt("ID"),
                 rs.getString("SubjectID"),
                 rs.getString("SubjectName"),
                 rs.getInt("ECTS"));

         list.add(subject);
      }

      return list;
   }




}

