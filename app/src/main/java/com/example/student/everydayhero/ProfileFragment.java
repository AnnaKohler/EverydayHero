package com.example.student.everydayhero;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by Anna on 23.03.2017.
 */

public class ProfileFragment extends Fragment {

    public static String EXTRA_PROFILE_EDIT="com.everydayhero.profile_edit";

    public static ProfileFragment newInstance(boolean edit) {

        Bundle args = new Bundle();
        args.putBoolean(EXTRA_PROFILE_EDIT, edit);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean edit = getArguments().getBoolean(EXTRA_PROFILE_EDIT); //TODO: Добавить возможность редактирования профиля
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView txtUsername=(TextView) v.findViewById(R.id.txtUserName);
        txtUsername.setText(User.getProfile(getContext()).getName());

        return v;
    }
}
