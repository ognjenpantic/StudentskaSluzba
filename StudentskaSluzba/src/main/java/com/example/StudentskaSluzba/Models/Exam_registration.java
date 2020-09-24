package com.example.StudentskaSluzba.Models;

public class Exam_registration {

    private int ID;
    private String subjectID;
    private String subjectName;
    private int index;
    private int year;
    private int ECTS;

    public Exam_registration(int ID, String subjectID, String subjectName, int index, int year, int ECTS) {
        this.ID = ID;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.index = index;
        this.year = year;
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

    public int getIndex() {
        return index;
    }

    public int getYear() {
        return year;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }
}
