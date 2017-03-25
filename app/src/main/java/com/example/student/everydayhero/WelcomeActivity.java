package com.example.student.everydayhero;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class WelcomeActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment createFragment() {
        return new WelcomeFragment();
    }
}
