package com.example.student.everydayhero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Anna on 22.03.2017.
 */

public class TodayFragment extends Fragment {

    private static final String EXTRA_TODAY_DATE = "com.everydayhero.today_date";
    private static final String EXTRA_TODAY_MOOD = "com.everydayhero.today_mood";
    private Date mDate;
    private int mMood;
    private TextView txtDate;

    public TodayFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_TODAY_DATE, new Date());

        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_today, container, false);
        txtDate=(TextView) v.findViewById(R.id.txtTodayDate);
        txtDate.setText(new Date().toString());
        return v;
    }
}
