package com.example.student.everydayhero;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ObjectiveListFragment extends ListFragment {

    private DBHandler mDBHandler;

    private ObjectiveListAdapter mListAdapter;


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getActivity(), ObjectiveReviewActivity.class);

        intent.putExtra(ObjectiveFragment.EXTRA_OBJECTIVE_ID, (int)id);
        intent.putExtra(ObjectiveFragment.EXTRA_OBJECTIVE_MODE, 0);

        startActivity(intent);

    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBHandler=new DBHandler(getContext());
        mListAdapter=new ObjectiveListAdapter((ArrayList<Objective>) mDBHandler.getAllObjectives());
        setListAdapter(mListAdapter);
    }
    public void onResume(){

        /*mListAdapter=new ObjectiveListAdapter((ArrayList<Objective>) mDBHandler.getAllObjectives());
        ((ObjectiveListAdapter)getListAdapter()).notifyDataSetChanged();*/
        super.onResume();
        mListAdapter.updateItems((ArrayList<Objective>) mDBHandler.getAllObjectives());
    }


    class ObjectiveListAdapter extends ArrayAdapter<Objective>{
        private ArrayList<Objective> mObjectives;
        public ObjectiveListAdapter(ArrayList<Objective> objectives){
            super(getActivity(), 0, objectives);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_objective, null);
            }
            Objective objective = mDBHandler.getAllObjectives().get(position);

            TextView txtTitle = (TextView) convertView.findViewById(R.id.txtViewTitle);
            txtTitle.setText(objective.getTitle());
            TextView txtGroup = (TextView) convertView.findViewById(R.id.txtViewGroup);
            txtGroup.setText(objective.getGroup());
            //ImageButton btnOptions = (ImageButton) convertView.findViewById(R.id.btnOptions); //TODO: возможность удалять цели

            return convertView;
        }

        public void updateItems(ArrayList<Objective> objectives){
            this.mObjectives=objectives;
            notifyDataSetChanged();
        }
    }

}
