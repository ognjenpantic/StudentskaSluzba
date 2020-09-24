package com.example.StudentskaSluzba;

import com.example.StudentskaSluzba.Database.Database;
import com.example.StudentskaSluzba.Database.DatabaseSecurity;
import com.example.StudentskaSluzba.Models.Professor;
import com.example.StudentskaSluzba.Models.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.crypto.Data;

@SpringBootApplication
public class StudentskaSluzbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentskaSluzbaApplication.class, args);


	}
}