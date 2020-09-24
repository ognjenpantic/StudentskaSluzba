package com.example.StudentskaSluzba.Models;

public class Subject {
    private int ID;
    private String subjectID;
    private String subjectName;
    private int ECTS;

    public Subject(){

    }

    public Subject(int ID, String subjectID, String subjectName, int ECTS) {
        this.ID = ID;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.ECTS = ECTS;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }
}
