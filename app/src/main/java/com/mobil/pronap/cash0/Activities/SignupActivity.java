package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.mobil.pronap.cash0.R;

public class SignupActivity extends AppCompatActivity {


    //Informations personnelles
    AutoCompleteTextView tvFName;
    AutoCompleteTextView tvLName;
    AutoCompleteTextView tvTel;
    AutoCompleteTextView tvAdress;

    //Informations bancaires
    Spinner spBankChooser;
    AutoCompleteTextView tvAccountNumber;
    AutoCompleteTextView tvCardNumber;
    AutoCompleteTextView tvCardDate;

    Button btnRegister;
    Intent i;

    Toolbar customToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init_views();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


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


    public void init_views(){
        tvFName = (AutoCompleteTextView) findViewById(R.id.tvFName);
        tvLName = (AutoCompleteTextView) findViewById(R.id.tvLName);
        tvTel = (AutoCompleteTextView) findViewById(R.id.tvTel);
        tvAdress = (AutoCompleteTextView) findViewById(R.id.tvAdress);
        tvAccountNumber = (AutoCompleteTextView) findViewById(R.id.tvAccountNumber);
        tvCardNumber = (AutoCompleteTextView) findViewById(R.id.tvCardNumber);
        tvCardDate = (AutoCompleteTextView) findViewById(R.id.tvCardDate);
        spBankChooser = (Spinner) findViewById(R.id.spBankChooser);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }



}
