package com.mobil.pronap.cash0.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.mobil.pronap.cash0.R;

public class ProfilActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerSlidingTabStrip tabStrip;
    TextView tvFName;  // for User first name
    TextView tvLName;   // for user last name
    TextView tvDate; // inscription date
    ImageView ivProfil;

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

        // ******* adapter missing
        //viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabStrip.setViewPager(viewPager);

        tvFName = (TextView) findViewById(R.id.tvFName);
        tvLName = (TextView) findViewById(R.id.tvLName);
        ivProfil = (ImageView) findViewById(R.id.ivProfil);
        tvDate = (TextView) findViewById(R.id.tvDate);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
