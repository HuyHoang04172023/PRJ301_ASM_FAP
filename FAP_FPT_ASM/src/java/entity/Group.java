/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hoang
 */
public class Group {
    private int id;
    private String name;
    private Subject subject;
    private Lecturer lecturer;
    private Room room;
    private Date startdate;
    private int timefirst;
    private int timesecond;
    private int dayfirst;
    private int daysecond;
    private int totalslot;
    private ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public int getTimefirst() {
        return timefirst;
    }

    public void setTimefirst(int timefirst) {
        this.timefirst = timefirst;
    }

    public int getTimesecond() {
        return timesecond;
    }

    public void setTimesecond(int timesecond) {
        this.timesecond = timesecond;
    }

    public int getDayfirst() {
        return dayfirst;
    }

    public void setDayfirst(int dayfirst) {
        this.dayfirst = dayfirst;
    }

    public int getDaysecond() {
        return daysecond;
    }

    public void setDaysecond(int daysecond) {
        this.daysecond = daysecond;
    }

    public int getTotalslot() {
        return totalslot;
    }

    public void setTotalslot(int totalslot) {
        this.totalslot = totalslot;
    }
    
    
}
