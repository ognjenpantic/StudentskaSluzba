package com.example.StudentskaSluzba.Controllers;

import com.example.StudentskaSluzba.Database.Database;
import com.example.StudentskaSluzba.Database.DatabaseSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLException;

@Controller
public class StudentController {
    Database db = new Database();
    DatabaseSecurity dbs=new DatabaseSecurity();

    @GetMapping("/student")
    private String student() {
        return "student_homepage";
    }

    @GetMapping("/student_subs")
    private String getSubs(Model model, Principal principal) throws SQLException {
        model.addAttribute("availableSubs", db.getListOfSubs("NOT IN", dbs.getID((principal))));

        return "student_subs";
    }



    @GetMapping("/student_regs")
    private String getRegs(Model model, Principal principal) throws SQLException {

        model.addAttribute("RegisteredExams",db.getListOfRegs(dbs.getID(principal)));

        return "student_regs";
    }

    @RequestMapping("/student_subs/reg")
    private String RegExam(@RequestParam(value = "SubjectID") String SubjectID, Principal principal) throws SQLException {

        db.addReg(dbs.getID(principal), SubjectID);
        return "redirect:/student_subs";
    }

    @GetMapping("/student_passed")
    private String getPassed(Model model, Principal principal) throws SQLException {
        model.addAttribute("passedExams", db.getListOfPassedX(dbs.getID(principal)));

        return "student_passed";
    }


}
