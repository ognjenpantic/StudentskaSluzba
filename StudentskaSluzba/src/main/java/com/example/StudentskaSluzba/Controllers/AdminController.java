package com.example.StudentskaSluzba.Controllers;


import com.example.StudentskaSluzba.Database.Database;
import com.example.StudentskaSluzba.Database.DatabaseSecurity;
import com.example.StudentskaSluzba.Models.Professor;
import com.example.StudentskaSluzba.Models.Student;
import com.example.StudentskaSluzba.Models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AdminController {

    Database db=new Database();
    DatabaseSecurity dbs = new DatabaseSecurity();

    @GetMapping("/admin")
    public String home()
    {
        return "admin_homepage";
    }

    @GetMapping("/admin_professors")
    public String showProfessors(Model model)throws SQLException {
        model.addAttribute("professors", db.getListOfProf());
        model.addAttribute("professor", new Professor());

        return "admin_professors";
    }

    @GetMapping("/admin_students")
    public String showStudents(Model model)throws SQLException{
        model.addAttribute("students",db.getListOfStudents());
        model.addAttribute("student",new Student());
        return "admin_students";
    }

    @GetMapping("/admin_subjects")
        public String showSubjects(Model model) throws SQLException {
        model.addAttribute("subjects",db.getListOfSubs());
        model.addAttribute("subject",new Subject());
        return "admin_subjects";
    }

    @PostMapping("/admin_professors")
    public String addProf(@ModelAttribute Professor professor)throws SQLException{
        db.addProfToDB(professor);

        List<Professor> list = db.getListOfProf();
        int ID  = list.get(list.size() - 1).getID();
        professor.setID(ID);
        dbs.addProfessor(professor);
        return "redirect:/admin_professors";
    }
    @PostMapping("/admin_students")
    public String addStud(@ModelAttribute Student student)throws SQLException{
        db.addStudentToDB(student);

        List<Student> list = db.getListOfStudents();
        int ID  = list.get(list.size() - 1).getID();
        student.setID(ID);
        dbs.addStudent(student);
        return "redirect:/admin_students";
    }
    @PostMapping("/admin_subjects")
    public String addSub(@ModelAttribute Subject subject)throws SQLException{
        db.addSubToDB(subject);

        List<Subject> list = db.getListOfSubs();
        int ID  = list.get(list.size() - 1).getID();
        subject.setID(ID);

        return "redirect:/admin_subjects";
    }
    @RequestMapping(value="admin_professors/delete")
    private String deleteProf(@RequestParam (value = "ID") String ID){
        String sql="DELETE FROM `professors` WHERE `professors`.`ID` = " + ID;
        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/admin_professors";
    }
    @RequestMapping(value="admin_students/delete")
    private String deleteStudent(@RequestParam(name="ID") String ID){
        String sql="DELETE FROM `students` WHERE `students`.`ID` = " + ID;

        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/admin_students";
    }
    @RequestMapping(value="admin_subjects/delete")
    private String deleteSub(@RequestParam(name="ID") String ID){
        String sql="DELETE FROM `subjects` WHERE `subjects`.`ID` = " + ID;


        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/admin_subjects";
    }
    @RequestMapping("/admin_professors/edit")
    private String editProf(@RequestParam(value ="ID", required = true) String  ID,
                                 @RequestParam(value ="FirstName", required = true) String FirstName,
                                 @RequestParam(value ="LastName", required = true) String LastName) {


        String sql="UPDATE `professors` SET `FirstName` = '"+ FirstName +
                "', `LastName` = '"+ LastName +
                "' WHERE `professors`.`ID` = "+ ID;

        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/admin_professors";
    }
    @RequestMapping("/admin_students/edit")
    private String editStudent(@RequestParam(value ="ID", required = true) String  ID,
                                @RequestParam(value ="FirstName", required = true) String FirstName,
                                @RequestParam(value ="LastName", required = true) String LastName,
                                @RequestParam (value = "IndexNumber", required = true) String IndexNumber,
                               @RequestParam (value = "EnrollmentYear", required = true) String EnrollmentYear,
                               @RequestParam (value = "Grade", required = true) String Grade) {


        String sql="UPDATE `students` SET `FirstName` = '"+ FirstName +
                "', `LastName` = '"+ LastName +
                "', `IndexNumber` = '"+ IndexNumber +
                "', `EnrollmentYear` = '"+ EnrollmentYear +
                "', `Grade` = '"+ Grade +
                "' WHERE `students`.`ID` = "+ ID;

        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/admin_students";
    }
    @RequestMapping("/admin_subjects/edit")
    private String editSub(@RequestParam(value ="ID", required = true) String  ID,
                            @RequestParam(value ="SubjectID", required = true) String SubjectID,
                            @RequestParam(value ="SubjectName", required = true) String SubjectName,
                           @RequestParam (value = "ECTS", required = true) String ECTS) {


        String sql="UPDATE `subjects` SET `SubjectID` = '"+ SubjectID +
                "', `SubjectName` = '"+ SubjectName +
                "', `ECTS` = '"+ ECTS +
                "' WHERE `subjects`.`ID` = "+ ID;

        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/admin_subjects";
    }
}
