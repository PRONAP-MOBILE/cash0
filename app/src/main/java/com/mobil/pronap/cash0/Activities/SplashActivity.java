package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mobil.pronap.cash0.R;

public class SplashActivity extends AppCompatActivity {

    ImageView ivLogo;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);


        //for test - must be removed
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(SplashActivity.this, MainActivity.class );
                startActivity(i);
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
