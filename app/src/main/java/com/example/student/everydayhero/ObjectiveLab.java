package com.example.student.everydayhero;

import android.content.Context;

import java.util.ArrayList;


/**
 * Created by student1 on 09.01.17.
 */

public class ObjectiveLab {

    private ArrayList<Objective> mObjectives;

    private static ObjectiveLab sObjectiveLab;
    private Context mAppContext;

    private ObjectiveLab(Context AppContext){
        mAppContext=AppContext;
        mObjectives=new ArrayList<Objective>();
    }

    public static ObjectiveLab get(Context c){
        if (sObjectiveLab ==null){
            sObjectiveLab =new ObjectiveLab(c.getApplicationContext());
        }
        return sObjectiveLab;
    }
    public Objective getObjective(String ObjTitle){
        for(Objective o: mObjectives){
            if(o.getTitle()==ObjTitle){
                return o;
            }
        }
        return null;
    }
    Objective obj0=new Objective("The First One");
    Objective obj1=new Objective("The Second One");
    Objective obj2=new Objective("The Third One");
    Objective obj3=new Objective("The Fourth One");
    Objective obj4=new Objective("The Fifth One");



}
