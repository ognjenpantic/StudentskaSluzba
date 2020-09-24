package com.example.StudentskaSluzba.Models;

public class Student {

    private int ID;
    private String first;
    private String last;
    private Integer index = null;
    private Integer year = null;
    private int grade;

    public Student(){

    }

    public Student(int ID, String first, String last, int index, int year, int grade) {
        this.ID = ID;
        this.first = first;
        this.last = last;
        this.index = index;
        this.year = year;
        this.grade = grade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
