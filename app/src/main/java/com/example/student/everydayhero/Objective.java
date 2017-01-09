package com.example.student.everydayhero;

/**
 * Created by student1 on 09.01.17.
 */

public class Objective {
    private String Title;
    private String Details;
    private int days;

    Objective(String s){
        this.Title=s;
        this.days=0;
        this.Details="";
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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

}
