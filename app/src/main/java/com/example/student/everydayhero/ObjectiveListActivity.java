package com.example.student.everydayhero;

import android.support.v4.app.Fragment;



public class ObjectiveListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ObjectiveListFragment();
    }
}
