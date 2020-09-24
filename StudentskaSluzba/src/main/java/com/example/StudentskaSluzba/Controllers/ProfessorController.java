package com.example.StudentskaSluzba.Controllers;

import com.example.StudentskaSluzba.Database.Database;
import com.example.StudentskaSluzba.Database.DatabaseSecurity;
import com.example.StudentskaSluzba.Models.Prof_sub;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLException;

@Controller
public class ProfessorController {

    private Database db = new Database();
    private DatabaseSecurity dbs = new DatabaseSecurity();

    @GetMapping("/professor")
    private String professor(){return "professor_homepage";
    }
    @GetMapping("/professor_subs")
    private String profSubs(Model model, Principal principal) throws SQLException{
        model.addAttribute("FreeSubjects", db.getFreeSubjects(dbs.getID(principal)));
        model.addAttribute("MySubjects",db.getProf_sub(dbs.getID(principal)));
        return "professor_subs";
    }

    @RequestMapping(value = "/professor_subs/select")
    private String selectSubs(@RequestParam(value = "SubjectID") String SubjectID, Principal principal) throws SQLException {


        Prof_sub prof_sub = new Prof_sub(SubjectID,dbs.getID(principal));
        db.addProfsubtoDB(prof_sub);
        return "redirect:/professor_subs";
    }

    @RequestMapping(value = "/professor_subs/cancel")
    private String cancelSubs(@RequestParam(value = "SubjectID") String SubjectID, Principal principal) throws SQLException {

        String sql = "DELETE FROM `prof_sub` WHERE `prof_sub`.`ProfessorID` ='" + dbs.getID(principal)  +
                "' AND `prof_sub`.`SubjectID` = '" + SubjectID +"'";

        try {
            System.out.println("Processing...");
            db.stmt.executeQuery(sql);
            System.out.println("Done!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "redirect:/professor_subs";
    }

    @GetMapping("professor_applications")
    private String getApplications(Model model, Principal principal) throws SQLException {
        model.addAttribute("Applications", db.getApplicationList(dbs.getID(principal)));

        return "/professor_applications";
    }

    @RequestMapping("/professor_applications/grade")
    private String gradeStudent(@RequestParam(value ="SubjectID", required = true) String  SubjectID,
                                 @RequestParam(value ="SubjectName", required = true) String SubjectName,
                                 @RequestParam(value ="StudentID", required = true) int StudentID,
                                 @RequestParam(value ="Mark", required = true) int Mark,
                                @RequestParam(value = "ECTS",required = true)int ECTS)  {

        if(Mark > 5 && Mark <= 10)
            db.addMark(StudentID,SubjectID,SubjectName,Mark,ECTS);
        else if(Mark == 5)
            db.delReg(StudentID,SubjectID);




        return "redirect:/professor_applications";
    }
    }


