package com.example.student.everydayhero;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anna on 22.03.2017.
 */

public class TodayFragment extends Fragment {

    private static final String EXTRA_TODAY_DATE_MILLIS = "com.everydayhero.today_date_millis";
    private static final String EXTRA_TODAY_MOOD = "com.everydayhero.today_mood";
    private Date mDate;
    private String formatedString;
    private int mMood;
    private TextView txtDate;
    private SimpleDateFormat mDateFormatter;
    private ArrayList<Objective> mObjectives;
    private ArrayList<Objective> crntObjectives;
    private DBHandler mDBHandler;
    private CurrentObjectivesAdapter mAdapter;
    private ListView listCrnt;

    public class CurrentObjectivesAdapter extends ArrayAdapter<Objective>{
        private final Context context;


        public CurrentObjectivesAdapter(Context c){

            super(c, 0, crntObjectives);
            context=c;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crntobjective, null);
            }
            final Objective o = crntObjectives.get(position);
            ImageView btnDone=(ImageView) convertView.findViewById(R.id.btnDone);
            TextView txtTitle=(TextView)convertView.findViewById(R.id.txtObjectiveTitle);
            txtTitle.setText(o.getTitle());
            TextView txtDays=(TextView)convertView.findViewById(R.id.txtDaysProgress);
            txtDays.setText(o.getDone()+"/"+o.getDuration());

            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    o.setDone(o.getDone()+1);
                    mDBHandler.updateObjective(o);
                    Toast t = Toast.makeText(context, R.string.hoorayExclamation, Toast.LENGTH_LONG);
                    crntObjectives.remove(position);
                    this.notifyAll();
                    t.show();
                }
            });
            return convertView;
        }
    }

    public static TodayFragment newInstance(Long date_millis){
        Bundle args=new Bundle();
        args.putLong(EXTRA_TODAY_DATE_MILLIS, (new Date().getTime()));

        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return getResources().getConfiguration().getLocales().get(0);
        }
        else return getResources().getConfiguration().locale;
    }

    public static boolean isDateBetween(Date start, Date end, Date date){
        return  !date.before(start) && !date.after(end);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDate=new Date(getArguments().getLong(EXTRA_TODAY_DATE_MILLIS));

        //Formatting the Date
        mDateFormatter=new SimpleDateFormat("EEE, d MMMM, ''yy", getCurrentLocale());
        formatedString=mDateFormatter.format(mDate);

        mDBHandler=new DBHandler(getContext());
        mObjectives=(ArrayList<Objective>) mDBHandler.getAllObjectives();
        crntObjectives=new ArrayList<>();

        if(mObjectives.size()!=0) {
            for (Objective o : mObjectives) {
                //Adding days to start date
                Calendar cal = Calendar.getInstance();
                cal.setTime(o.getBeginDate());
                cal.add(Calendar.DATE, o.getDuration() - 1);
                Date endDate = cal.getTime();

                if (isDateBetween(o.getBeginDate(), endDate, new Date())) {
                    crntObjectives.add(o);
                }
            }
        }

        mAdapter=new CurrentObjectivesAdapter(getActivity());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listCrnt=(ListView)getActivity().findViewById(R.id.listview);
        if(listCrnt!=null){
            View v=inflater.inflate(R.layout.fragment_today, container, false);
            txtDate=(TextView) v.findViewById(R.id.txtTodayDate);
            txtDate.setText(formatedString);
            listCrnt.setClickable(false);
            listCrnt.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
        View v = inflater.inflate(R.layout.fragment_no_tasks, container, false);


        return v;
    }
}
