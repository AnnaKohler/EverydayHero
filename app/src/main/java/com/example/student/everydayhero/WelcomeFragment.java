package com.example.student.everydayhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by Anna on 24.03.2017.
 */

public class WelcomeFragment extends Fragment {
    private Button btnContinue;
    private EditText editName;
    private EditText editHeight;
    private EditText editWeight;
    private EditText editAge;
    private DBHandler mDBHandler;
    private User mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = new User();
        mDBHandler=new DBHandler(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        btnContinue=(Button)v.findViewById(R.id.btnContinue);
        editName=(EditText)v.findViewById(R.id.editName);
        editHeight=(EditText)v.findViewById(R.id.editHeight);
        editWeight=(EditText)v.findViewById(R.id.editWeight);
        editAge=(EditText)v.findViewById(R.id.editAge);

        final RadioButton maleBtn=(RadioButton) v.findViewById(R.id.radioButton5);
        final RadioButton femaleBtn=(RadioButton) v.findViewById(R.id.radioButton4);

        maleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser.setSex(1);
                femaleBtn.setActivated(false);
            }

        });

        femaleBtn.setOnClickListener(new View.OnClickListener() { //TODO: отладка
            @Override
            public void onClick(View view) {
                mUser.setSex(0);
                maleBtn.setActivated(false);
            }

        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUser.setName(editName.getText().toString());
                mUser.setHeight(Float.parseFloat(editHeight.getText().toString()));
                mUser.setWeight(Float.parseFloat(editWeight.getText().toString()));
                mUser.setAge(Integer.parseInt(editAge.getText().toString()));
                mDBHandler.addUser(mUser);

                Intent i=new Intent(getActivity(), MainTabActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return v;
    }
}
