package com.example.student.everydayhero;

import java.util.Date;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
