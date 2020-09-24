package com.example.StudentskaSluzba.Models;

public class Professor {
    private int ID;
    private String first;
    private String last;

    public Professor(){

    }

    public Professor(int ID, String first, String last) {
        this.ID = ID;
        this.first = first;
        this.last = last;
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
}
