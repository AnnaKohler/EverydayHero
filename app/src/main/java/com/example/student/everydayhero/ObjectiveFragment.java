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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anna on 27.01.2017.
 */

public class ObjectiveFragment extends Fragment {

    public static ObjectiveFragment newInstance(int mode){
        Bundle args=new Bundle();
        args.putInt(EXTRA_OBJECTIVE_MODE, mode);
        ObjectiveFragment fragment = new ObjectiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ObjectiveFragment newInstance(String title, int mode){
        Bundle args=new Bundle();
        args.putInt(EXTRA_OBJECTIVE_MODE, mode);
        args.putString(EXTRA_OBJECTIVE_ID, title);

        ObjectiveFragment fragment = new ObjectiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static String EXTRA_OBJECTIVE_ID="com.everydayhero.objective_id";
    public static String EXTRA_OBJECTIVE_MODE="com.everydayhero.objective_mode";


    private Objective mObjective;
    private TextView txtTitle;
    private Spinner spnStartDate;
    private TextView txtGroup;
    private TextView separatorDetails;
    private TextView separatorProgress;
    private TextView txtDetails;
    private TextView txtProgressLine;
    private TextView txtProgress;
    private ProgressBar progressBar;
    private int objMode=0;
    private EditText durationDays;
    private EditText startingDate;
    private ImageButton btnDone;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int objectiveMode=getArguments().getInt(EXTRA_OBJECTIVE_MODE);
        objMode=objectiveMode;

        if(objectiveMode!=2){
            String objectiveId=getArguments().getString(EXTRA_OBJECTIVE_ID);
            mObjective=ObjectiveLab.get(getActivity()).getObjective(objectiveId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        switch (objMode) {

            case 0:
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
                        Intent i = new Intent(getActivity(), ObjectiveReviewActivity.class);
                        i.putExtra(EXTRA_OBJECTIVE_MODE, 1);
                        i.putExtra(EXTRA_OBJECTIVE_ID, mObjective.getTitle());
                        startActivity(i);

                    }
                });
                return v;


            case 1:
                v = inflater.inflate(R.layout.fragment_objective_edit, container, false);

                txtDetails = (EditText) v.findViewById(R.id.txtDetails);
                txtDetails.setText(mObjective.getDetails());
                txtDetails.setHint("You can add some details here");
                txtDetails.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

                separatorDetails = (TextView) v.findViewById(R.id.separatorDetails);
                separatorDetails.setText(R.string.separator_details);

                txtTitle = (EditText) v.findViewById(R.id.txtTitle);
                txtTitle.setText(mObjective.getTitle());

                durationDays = (EditText) v.findViewById(R.id.editDays);
                durationDays.setText(mObjective.getDuration() + "");

                /*spnStartDate=(Spinner)v.findViewById(R.id.spnStartingDate);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        getContext(), R.array.startingDates, android.R.layout.simple_list_item_1);
                spnStartDate.setAdapter(adapter);*/

                btnDone = (ImageButton) v.findViewById(R.id.imgButton_done);
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Updating Objective Details
                        mObjective.setTitle(txtTitle.getText().toString());
                        mObjective.setDetails(txtDetails.getText().toString());
                        mObjective.setDuration(Integer.parseInt(durationDays.getText().toString()));

                        Intent i = new Intent(getActivity(), ObjectiveReviewActivity.class);
                        i.putExtra(EXTRA_OBJECTIVE_ID, mObjective.getTitle());
                        i.putExtra(EXTRA_OBJECTIVE_MODE, 0);
                        startActivity(i);
                        getActivity().finish();
                    }
                });

                return v;


            default:
                v = inflater.inflate(R.layout.fragment_objective_edit, container, false);

                txtDetails = (EditText) v.findViewById(R.id.txtDetails);

                txtDetails.setHint("You can add some details here");
                txtDetails.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

                separatorDetails = (TextView) v.findViewById(R.id.separatorDetails);
                separatorDetails.setText(R.string.separator_details);

                txtTitle = (EditText) v.findViewById(R.id.txtTitle);
                txtTitle.setHint("Objective's Title");

                durationDays = (EditText) v.findViewById(R.id.editDays);
                durationDays.setHint("21");

                btnDone = (ImageButton) v.findViewById(R.id.imgButton_done);
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Updating Objective Details
                        mObjective=new Objective();
                        mObjective.setTitle(txtTitle.getText().toString());
                        mObjective.setDetails(txtDetails.getText().toString());

                        if(durationDays.getText().toString().equals("")){
                            Toast toast = Toast.makeText(getActivity(),
                                    "Enter the objective's duration!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            mObjective.setDuration(Integer.parseInt(durationDays.getText().toString()));

                            if (mObjective.getTitle().equals("")) {
                                Toast toast = Toast.makeText(getActivity(),
                                        "Enter the objective's title!", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                //Intent i = new Intent(getActivity(), MainTabActivity.class);
                                ObjectiveLab.get(getActivity()).add(mObjective);
                                //startActivity(i);
                                getActivity().finish();
                            }
                        }
                    }
                });
                return v;
        }
    }
}
