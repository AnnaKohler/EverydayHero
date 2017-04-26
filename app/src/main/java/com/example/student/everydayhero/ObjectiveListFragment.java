package com.example.student.everydayhero;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Anna on 11.01.2017.
 */

public class ObjectiveListFragment extends ListFragment {

    private DBHandler mDBHandler;
    private Cursor mCursor;
    private static final String TABLE_OBJECTIVES = "objectives";
    private String selectQuery="SELECT * FROM "+TABLE_OBJECTIVES;
    private ObjectiveCursorAdapter mCursorAdapter;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getActivity(), ObjectiveReviewActivity.class);
        ArrayList<Objective> objectives = (ArrayList<Objective>) mDBHandler.getAllObjectives();
        Objective objective = objectives.get(position);
        intent.putExtra(ObjectiveFragment.EXTRA_OBJECTIVE_ID, position);
        intent.putExtra(ObjectiveFragment.EXTRA_OBJECTIVE_MODE, 0);
        startActivity(intent);
        Log.e("ATTENTION", "onListItemClick: item was clicked");
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBHandler=new DBHandler(getContext());
        Cursor mCursor = mDBHandler.getReadableDatabase().rawQuery(selectQuery, null);
        mCursorAdapter =new ObjectiveCursorAdapter(getContext(), mCursor);
        this.setListAdapter(mCursorAdapter);

        //TODO: Нахрен убрать курсор адаптер

    }
    public void onResume(){
        super.onResume();
        mCursor= mDBHandler.getReadableDatabase().rawQuery(selectQuery, null);
        mCursorAdapter.changeCursor(mCursor);
        mCursorAdapter.notifyDataSetChanged();

    }
    //TODO: Заменить ненужный курсорадаптер на лист адаптер
    private class ObjectiveCursorAdapter extends CursorAdapter implements AdapterView.OnItemClickListener{

        public ObjectiveCursorAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_item_objective, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView txtViewTitle = (TextView) view.findViewById(R.id.txtViewTitle);
            TextView txtViewGroup = (TextView) view.findViewById(R.id.txtViewGroup);
            ImageButton btnOptions = (ImageButton) view.findViewById(R.id.btnOptions);

            String txtTitle = cursor.getString(cursor.getColumnIndexOrThrow("obj_title"));
            String txtGroup = cursor.getString(cursor.getColumnIndexOrThrow("obj_group"));
            txtViewTitle.setText(txtTitle);

            txtViewGroup.setText(txtGroup);
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public boolean isEnabled(int position)
        {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }
    }

}
