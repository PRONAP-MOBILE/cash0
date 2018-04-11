package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mobil.pronap.cash0.R;

public class SplashActivity extends AppCompatActivity {

    ImageView ivLogo;
    int time = 3000;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                i = new Intent(SplashActivity.this, MainActivity.class );
                startActivity(i);
                overridePendingTransition(R.anim.right, R.anim.left);
                finish();

            }
        }, time);



    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
