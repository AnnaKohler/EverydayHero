package com.example.student.everydayhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Anna on 27.01.2017.
 */

public class ObjectiveFragment extends Fragment {

    public static ObjectiveFragment newInstance(String title, boolean edit){
        Bundle args=new Bundle();
        args.putBoolean(EXTRA_OBJECTIVE_EDIT, edit);
        args.putString(EXTRA_OBJECTIVE_ID, title);
        ObjectiveFragment fragment = new ObjectiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static String EXTRA_OBJECTIVE_ID="com.everydayhero.objective_id";
    public static String EXTRA_OBJECTIVE_EDIT="com.everydayhero.objective_edit";

    private Objective mObjective;
    private TextView txtTitle;
    private TextView txtGroup;
    private TextView separatorDetails;
    private TextView separatorProgress;
    private TextView txtDetails;
    private TextView txtProgressLine;
    private TextView txtProgress;
    private ProgressBar progressBar;
    private boolean edit=false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String objectiveId=getArguments().getString(EXTRA_OBJECTIVE_ID);
        boolean objectiveEdit=getArguments().getBoolean(EXTRA_OBJECTIVE_EDIT);
        if(objectiveEdit){
            edit=true;
        }
        mObjective=ObjectiveLab.get(getActivity()).getObjective(objectiveId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(!edit) {
            View v = inflater.inflate(R.layout.fragment_objective, container, false);

            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtTitle.setText(mObjective.getTitle());

            txtGroup = (TextView) v.findViewById(R.id.txtGroupTitle);
            txtGroup.setText(mObjective.getGroup());

            separatorDetails = (TextView) v.findViewById(R.id.separatorDetails);
            separatorDetails.setText(R.string.separator_details);

            separatorProgress = (TextView) v.findViewById(R.id.separatorProgress);
            separatorProgress.setText(R.string.separator_progress);

            txtDetails = (TextView) v.findViewById(R.id.txtDetails);
            txtDetails.setText(mObjective.getDetails());

            txtProgressLine = (TextView) v.findViewById(R.id.txtProgressLine);
            txtProgressLine.setText(R.string.progress);

            txtProgress = (TextView) v.findViewById(R.id.txtProgress);
            txtProgress.setText(mObjective.getDone() + "/" + mObjective.getDuration());


            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
            progressBar.setProgress(100 / mObjective.getDuration() * mObjective.getDone());

            Button editBtn = (Button) v.findViewById(R.id.btnEdit);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), ObjectiveEditActivity.class);
                    i.putExtra(EXTRA_OBJECTIVE_EDIT, true);
                    i.putExtra(EXTRA_OBJECTIVE_ID, mObjective.getTitle());
                    startActivity(i);
                }
            });
            return v;
        }
        View v= inflater.inflate(R.layout.fragment_objective_edit, container, false);

        txtDetails = (TextView) v.findViewById(R.id.txtDetails);
        txtDetails.setText(mObjective.getDetails());
        txtDetails.setHint("You can add some details here");
        txtDetails.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        separatorDetails = (TextView) v.findViewById(R.id.separatorDetails);
        separatorDetails.setText(R.string.separator_details);

        return v;
    }
}
