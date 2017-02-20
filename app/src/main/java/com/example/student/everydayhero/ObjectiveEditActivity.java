package com.example.student.everydayhero;

import android.support.v4.app.Fragment;

/**
 * Created by Anna on 18.02.2017.
 */

public class ObjectiveEditActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        String objectiveId=getIntent().getStringExtra(ObjectiveFragment.EXTRA_OBJECTIVE_ID);
        return ObjectiveFragment.newInstance(objectiveId, true);

    }
}
