package com.example.student.everydayhero;

import java.util.Date;

public class Objective {
    private int _id;
    private String Title="";
    private String Group="";
    private String Details;
    private int duration;
    private int doneDays;
    private Date beginDate;


    Objective(String s){
        this.Title=s;
        this.duration=1;
        this.Details="";
        beginDate=new Date();
    }
    Objective(){
        this.Title="";
        this.duration=1;
        this.Details="";
        beginDate=new Date();

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
        return doneDays;
    }
    public void setDone(int done) {
        this.doneDays = done;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }
}
