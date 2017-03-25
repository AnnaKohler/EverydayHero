package com.example.student.everydayhero;

import android.support.v4.app.Fragment;

/**
 * Created by Anna on 01.02.2017.
 */

public class ObjectiveReviewActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        int mode = getIntent().getIntExtra(ObjectiveFragment.EXTRA_OBJECTIVE_MODE, 0);
        if(mode!=2){
            String objectiveId = getIntent().getStringExtra(ObjectiveFragment.EXTRA_OBJECTIVE_ID);
            return ObjectiveFragment.newInstance(objectiveId, mode);
        }
        return ObjectiveFragment.newInstance(mode);


    }
}
