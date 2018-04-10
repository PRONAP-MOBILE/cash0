package com.mobil.pronap.cash0.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobil.pronap.cash0.R;

public class MainActivity extends AppCompatActivity {

    //ImageButton ibProfil;
    ImageView ivProfil;
    Button btnBuy;
    Button btnSell;
    TextView tvSignUp;
    Intent i;
    Toolbar customToolbar;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        init_views();

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if user already login
                //Make redirection for the correct screen
                if(sharedPreferences.getString("infoUser", null)==null || sharedPreferences.getString("infoUser", null).equals("")){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this, ProfilActivity.class));
                }
            }
        });



        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if user already login
                //Make the correct redirection
                if(sharedPreferences.getString("infoUser", null)==null || sharedPreferences.getString("infoUser", null).equals("")){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this, BuyActivity.class));
                }

            }
        });



        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Check if user already login
                //Make the correct redirection
                if(sharedPreferences.getString("infoUser", null)==null || sharedPreferences.getString("infoUser", null).equals("")){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this, DetailSellActivity.class));
                }
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
        //ibProfil = (ImageButton) findViewById(R.id.ibProfil);
        ivProfil = (ImageView) findViewById(R.id.ivProfil);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnSell = (Button) findViewById(R.id.btnSell);
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setClickable(true);
        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);
    }


    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.userProfil:
                i = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
