package com.example.student.everydayhero;

import java.util.Date;

/**
 * Created by student1 on 09.01.17.
 */

public class Objective {
    private String Title="";
    private String Group="";
    private String Details;
    private int duration;
    private int done;
    private Date beginDate;
    private Date endDate;

    Objective(String s){
        this.Title=s;
        this.duration=1;
        this.Details="";
        beginDate=new Date();
        endDate=new Date();

    }
    Objective(){
        this.Title="";
        this.duration=1;
        this.Details="";
        beginDate=new Date();
        endDate=new Date();

    }
    public String getGroup() { return Group; }

    public void setGroup(String group) {
        Group = group;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int days) {
        this.duration = days;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
