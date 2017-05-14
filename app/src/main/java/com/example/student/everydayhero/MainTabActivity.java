package com.example.student.everydayhero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainTabActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private DBHandler mDBHandler;
    public static String EXTRA_OBJECTIVE_MODE="com.everydayhero.objective_mode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up DBHandler
        if(mDBHandler==null) {
            mDBHandler = new DBHandler(getApplicationContext());
        }

        if(!mDBHandler.checkForTables("user")){
            Intent i=new Intent(this, WelcomeActivity.class);
            startActivity(i);
            finish();
        }




        setContentView(R.layout.activity_main_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        FragmentManager fm =getSupportFragmentManager();
        PagerAdapter adapter=new SectionsPagerAdapter(fm);

        mViewPager.setAdapter(adapter);


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.setupWithViewPager(mViewPager);



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                Intent i = new Intent(getApplication(), ObjectiveReviewActivity.class);
                i.putExtra(EXTRA_OBJECTIVE_MODE, 2);
                startActivity(i);
                finish();

            }
        });

        mViewPager.getAdapter().notifyDataSetChanged();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                fab.setVisibility(position==0?View.VISIBLE:View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    if(mDBHandler.getObjectivesCount()==0){
                    return new noObjectivesFragment();
                    }
                    return new ObjectiveListFragment();


                case 1: return TodayFragment.newInstance(); //pass the Date in case there will be another dates review
                case 2: return ProfileFragment.newInstance(false); //Profile edit=false
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.sectGoals);
                case 1:
                    return getString(R.string.sectToday);
                case 2:
                    return getString(R.string.sectProfile);
            }
            return null;
        }
    }
}
