package com.example.admin;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout homeTabs;
    ViewPager viewPager;
    DrawerLayout drawer;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /*
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Admin Name");

       // getData();
       // setTitle("sfse1");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      /*  if (id == R.id.nav_requests) {
            Intent requests = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Requests");

        } else if (id == R.id.nav_doctors) {
            Intent requests = new Intent(getApplicationContext(),Doctor.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Doctors");

        } else if (id == R.id.nav_patients) {
            Intent requests = new Intent(getApplicationContext(), PatientActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Patients");

        } else if (id == R.id.nav_labs) {
            Intent requests = new Intent(getApplicationContext(),LabsActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Labs");

        } else if (id == R.id.nav_pharmacy) {
            Intent requests = new Intent(getApplicationContext(),PharmacyActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Pharmacy");

        } /*else if (id == R.id.nav_pharmacy) {
            Intent requests = new Intent(getApplicationContext(),PharmacyActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Pharmacy");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {
            Intent requests = new Intent(getApplicationContext(),FeedbackActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Feedback");

        } else if (id == R.id.nav_faq) {
            Intent requests = new Intent(getApplicationContext(),FAQActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("FAQ");

        }else if (id == R.id.nav_help) {
            Intent requests = new Intent(getApplicationContext(),HelpActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Help");

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_home, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            return rootView;
        }
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
            //Returning the current tabs
            switch (position) {
                case 0:
                    Pending_tab1 tab1 = new Pending_tab1();
                    return tab1;
                case 1:
                    Accepted_tab2 tab2 = new Accepted_tab2();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}




