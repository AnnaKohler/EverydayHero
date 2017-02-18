package com.example.student.everydayhero;

import android.support.v4.app.Fragment;

/**
 * Created by Anna on 01.02.2017.
 */

public class ObjectiveReviewActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        boolean edit = getIntent().getBooleanExtra(ObjectiveFragment.EXTRA_OBJECTIVE_EDIT, false);
        String objectiveId = getIntent().getStringExtra(ObjectiveFragment.EXTRA_OBJECTIVE_ID);
        return ObjectiveFragment.newInstance(objectiveId, edit);

        //return ObjectiveEditFragment.newInstance(); //Как передать аргументы между фрагментами? TODO:Аргументы
    }
}
