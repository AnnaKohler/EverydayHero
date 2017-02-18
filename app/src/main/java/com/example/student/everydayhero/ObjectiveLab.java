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

        for(int i=0; i<10; i++){
            Objective o =new Objective(String.valueOf(i));
            o.setTitle(""+i);
            o.setDuration(i+1);
            o.setDetails("Details: I want that text to be very-very long so it takes a few lines");
            o.setGroup("Group Number " + i%2);
            o.setDone(o.getDuration()/2+i%2);
            mObjectives.add(o);
        }
    }

    public static ObjectiveLab get(Context c){
        if (sObjectiveLab ==null){
            sObjectiveLab =new ObjectiveLab(c.getApplicationContext());
        }
        return sObjectiveLab;
    }
    public ArrayList<Objective> getObjectives(){
        return mObjectives;
    }
    public Objective getObjective(String ObjTitle){
        for(Objective o: mObjectives){
            if(o.getTitle().equals(ObjTitle)){
                return o;
            }
        }
        return null;
    }


}
