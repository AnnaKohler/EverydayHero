package com.example.student.everydayhero;

import android.content.Context;

/**
 * Created by student1 on 09.01.17.
 */

public class User {
    private int age;
    private String name;
    private float weight;
    private float height;

    private static User sUser;

    private Context mAppContext;

    private User(Context context){
        mAppContext=context;
        age=18;
        name="";
        weight=1;
        height=1;
    }

    public static User getProfile(Context context){
        if(sUser==null) sUser = new User(context);
        return sUser;
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
