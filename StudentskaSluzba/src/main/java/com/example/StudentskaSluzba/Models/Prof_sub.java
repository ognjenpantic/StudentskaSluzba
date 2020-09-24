package com.example.StudentskaSluzba.Models;


public class Prof_sub {

        private String subjectID;
        private int professorID;

    public Prof_sub(String subjectID, int professorID) {
        this.subjectID = subjectID;
        this.professorID = professorID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }
}
