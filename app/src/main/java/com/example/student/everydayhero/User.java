package com.example.student.everydayhero;

import java.util.Date;

/**
 * Created by student1 on 09.01.17.
 */

public class User {
    private int age;
    private String name;
    private Date lastSeen;
    private float weight;
    private float height;


    private static User sUser;



    public User(){
        age=18;
        name="";
        lastSeen=new Date();
        lastSeen.setHours(0);
        weight=1;
        height=1;
    }


    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
