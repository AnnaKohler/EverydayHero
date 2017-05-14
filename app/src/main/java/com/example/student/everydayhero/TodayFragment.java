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
import android.widget.ProgressBar;
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

    private ArrayList<Objective> crntObjectives;
    private DBHandler mDBHandler;
    private CurrentObjectivesAdapter mAdapter;
    private ListView listCrnt;

    public class CurrentObjectivesAdapter extends ArrayAdapter<Objective>{
        private final Context context;
        private ArrayList<Objective> objectives;

        public CurrentObjectivesAdapter(Context c, ArrayList<Objective> crntobjectives){

            super(c, 0, crntobjectives);
            context=c;
            objectives=crntobjectives;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crntobjective, null);
            }
            Objective o = objectives.get(position);
            final ImageView btnDone=(ImageView) convertView.findViewById(R.id.btnDone);
            TextView txtTitle=(TextView)convertView.findViewById(R.id.txtObjectiveTitle);
            txtTitle.setText(o.getTitle());
            TextView txtDays=(TextView)convertView.findViewById(R.id.txtDaysProgress);
            txtDays.setText(o.getDone()+"/"+o.getDuration());
            ProgressBar progress=(ProgressBar)convertView.findViewById(R.id.progressBar);
            progress.setProgress(100 / o.getDuration() * o.getDone());

            btnDone.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                        Objective o = objectives.get(position);
                    if (o.getDuration() != o.getDone()) {
                        o.setDone(o.getDone() + 1);
                        mDBHandler.updateObjective(o);
                        Toast t;
                        if (o.getDuration() != o.getDone()) {
                            t = Toast.makeText(context, R.string.hoorayExclamation, Toast.LENGTH_SHORT);
                        } else {
                            t = Toast.makeText(context, R.string.didExclamation, Toast.LENGTH_SHORT);

                        }
                        t.show();

                        btnDone.setClickable(false);
                        crntObjectives.remove(position);
                        onResume();
                    }
                    else btnDone.setClickable(false);
                }

            });
            return convertView;
        }
    }

    public static TodayFragment newInstance(){
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

        crntObjectives=new ArrayList<>();

        if(mDBHandler.getAllObjectives().size()!=0) {
            for (Objective o : mDBHandler.getAllObjectives()) {

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


        mAdapter=new CurrentObjectivesAdapter(getActivity(), crntObjectives);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {

        mAdapter.clear();
        mAdapter.addAll(crntObjectives);
        mAdapter.notifyDataSetChanged();

        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_today, container, false);

        crntObjectives=new ArrayList<>();
        if(mDBHandler.getAllObjectives().size()!=0) {
            crntObjectives.clear();
            for (Objective o :mDBHandler.getAllObjectives()) {
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

        listCrnt=(ListView)v.findViewById(R.id.listview);

        listCrnt.setAdapter(new CurrentObjectivesAdapter(getActivity(), crntObjectives));


        if(crntObjectives.size()!=0){

            txtDate=(TextView) v.findViewById(R.id.txtTodayDate);
            txtDate.setText(formatedString);

            listCrnt.setClickable(false);
            listCrnt.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            return v;

        }
        v = inflater.inflate(R.layout.fragment_no_tasks, container, false);
        return v;
    }
}
