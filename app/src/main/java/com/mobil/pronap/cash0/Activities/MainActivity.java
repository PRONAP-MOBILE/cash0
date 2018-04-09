package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobil.pronap.cash0.R;

public class MainActivity extends AppCompatActivity {

    ImageButton ibProfil;
    Button btnBuy;
    Button btnSell;
    TextView tvSignUp;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_views();

        ibProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //i = new Intent(MainActivity.this, ProfilActivity.class);
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });



        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if user connected
                i = new Intent(MainActivity.this, BuyActivity.class);
                startActivity(i);
                //else intent -> (this, loginActivity)
            }
        });



        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if user connected
                i = new Intent(MainActivity.this, DetailSellActivity.class);
                startActivity(i);
                //else intent -> (this, loginActivity)
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });


    }

    public void init_views(){
        ibProfil = (ImageButton) findViewById(R.id.ibProfil);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnSell = (Button) findViewById(R.id.btnSell);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setClickable(true);

    }
}
