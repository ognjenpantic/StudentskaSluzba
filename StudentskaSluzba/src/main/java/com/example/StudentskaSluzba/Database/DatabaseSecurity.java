package com.example.StudentskaSluzba.Database;

import com.example.StudentskaSluzba.Models.Professor;
import com.example.StudentskaSluzba.Models.Student;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;
import java.sql.*;
public class DatabaseSecurity {

    private Connection conn = null;
    private Statement stmt = null;
    private int genericProf = 1;
    private int genericStud = 1;


    public DatabaseSecurity() {
        try {
            System.out.println("Connecting to Database Security...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/security", "root", ""
            );
            stmt = conn.createStatement();
            System.out.println("Connected.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addProfessor(Professor professor) throws SQLException {
        String sql;
        String pass = encodePass(professor.getLast());
        String username = (professor.getFirst().toLowerCase()) + "." + (professor.getLast().toLowerCase());
        if(checkUser(username))
        {
            username = username + genericProf;
            genericProf = genericProf + 1;
        }
        sql =   " INSERT INTO `users`(`Id`, `User`, `Pass`,`Role`)" +
                "VALUES ('" + professor.getID() + "', " +
                "'" + username + "', " +
                "'" + pass + "', " +
                "'ROLE_PROFESSOR')";

        try {
            System.out.println("Processing...");
            stmt.executeUpdate(sql);
            System.out.println("Done!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addStudent(Student student) throws SQLException {
        String sql;
        String pass = encodePass(student.getLast());
        String username = (student.getFirst().toLowerCase()) + "." + (student.getLast().toLowerCase());
        if(checkUser(username))
        {
            username = username + genericStud;
            genericStud = genericStud + 1;
        }
        sql =   " INSERT INTO `users`(`Id`, `User`, `Pass`,`Role`)" +
                "VALUES ('" + student.getID() + "', " +
                "'" + username + "', " +
                "'" + pass + "', " +
                "'ROLE_STUDENT')";

        try {
            System.out.println("Processing...");
            stmt.executeUpdate(sql);
            System.out.println("Done!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String encodePass(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(pass);
    }

    public int getID(Principal principal) throws SQLException {
        int id = 0;
        ResultSet rs = null;
        String sql = "SELECT * FROM `users`";
        try {
            System.out.println("Processing...");
            rs = stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (rs.next()) {
            if (rs.getString("User").equals(principal.getName())) {
                id = rs.getInt("ID");
                break;
            }
        }
        return id;
    }

    private boolean checkUser(String username) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM `users`";
        try {
            System.out.println("Processing...");
            rs = stmt.executeQuery(sql);
            System.out.println("Done.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (rs.next()) {
            if (rs.getString("User").equals(username)) {
                return true;
            }
        }

        return false;
    }

}
