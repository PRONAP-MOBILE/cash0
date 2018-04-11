package com.mobil.pronap.cash0.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.mobil.pronap.cash0.Adapters.SampleFragmentPagerAdapter;
import com.mobil.pronap.cash0.Fragments.UserInfo;
import com.mobil.pronap.cash0.Fragments.UserTransactions;
import com.mobil.pronap.cash0.R;

public class ProfilActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerSlidingTabStrip tabStrip;
    TextView tvFName;  // for User first name
    TextView tvLName;   // for user last name
    TextView tvDate; // inscription date
    ImageView ivProfil;
    Toolbar customToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        init_views();


    }


    // to inti views
    public void init_views(){

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // ******* adapter missing
        viewPager.setAdapter(new NotespagerAdapter(getSupportFragmentManager()));
        tabStrip.setViewPager(viewPager);

        tvFName = (TextView) findViewById(R.id.tvFName);
        tvLName = (TextView) findViewById(R.id.tvLName);
        ivProfil = (ImageView) findViewById(R.id.ivProfil);
        tvDate = (TextView) findViewById(R.id.tvDate);

        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public  class NotespagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 2;
        private String tabTitle[] = {"Info. Personelles", "Transaction"};

        public NotespagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                //Fragment for user's personal informations | data should be added on newInstance() method
                return UserInfo.newInstance();
            }else if (position == 1){
                //Fragment for user's transactions list | method newInstance() should be modified to pass data
                return UserTransactions.newInstance();
            }else{
                return  null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
