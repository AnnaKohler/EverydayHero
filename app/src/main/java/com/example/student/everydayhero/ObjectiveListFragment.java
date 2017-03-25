package com.example.student.everydayhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Anna on 11.01.2017.
 */

public class ObjectiveListFragment extends ListFragment {
    private ArrayList<Objective> mObjectives;


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Objective o = ((ObjectiveAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), ObjectiveReviewActivity.class);
        i.putExtra(ObjectiveFragment.EXTRA_OBJECTIVE_ID, o.getTitle());
        i.putExtra(ObjectiveFragment.EXTRA_OBJECTIVE_MODE, 0);
        startActivity(i);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mObjectives=ObjectiveLab.get(getActivity().getApplicationContext()).getObjectives();
        ObjectiveAdapter adapter=new ObjectiveAdapter(mObjectives);
        setListAdapter(adapter);
    }
    public void onResume(){
        super.onResume();
        ((ObjectiveAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class ObjectiveAdapter extends ArrayAdapter<Objective>{

        public ObjectiveAdapter(ArrayList<Objective> Objectives){
            super(getActivity(), android.R.layout.simple_list_item_1, Objectives);
        }

        public View getView(int position, View convertView, ViewGroup parent){

            if(convertView==null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_objective, null);
            }
            Objective o = getItem(position);

            TextView TitleTextView=(TextView) convertView.findViewById(R.id.txtViewTitle);
            TitleTextView.setText(o.getTitle());
            TextView GroupTextView=(TextView)convertView.findViewById(R.id.txtViewGroup);
            GroupTextView.setText(o.getGroup());
            ImageButton IconBtn=(ImageButton)convertView.findViewById(R.id.imageButton);
            IconBtn.setFocusable(false);
            IconBtn.setFocusableInTouchMode(false);

            ImageButton OptionsBtn=(ImageButton)convertView.findViewById(R.id.btnOptions);
            OptionsBtn.setFocusable(false);
            OptionsBtn.setFocusableInTouchMode(false);

            return convertView;

        }

    }
}
