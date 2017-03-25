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

/**
 * Created by Anna on 24.03.2017.
 */

public class WelcomeFragment extends Fragment {
    private Button btnContinue;
    private EditText editName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        btnContinue=(Button)v.findViewById(R.id.btnContinue);
        editName=(EditText)v.findViewById(R.id.editName);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.getProfile(getActivity()).setName(editName.getText().toString());

                Intent i=new Intent(getActivity(), MainTabActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
        return v;
    }
}
