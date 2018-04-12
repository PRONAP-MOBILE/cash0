package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobil.pronap.cash0.R;

public class SignupActivity extends AppCompatActivity {


    //Informations personnelles
    AutoCompleteTextView tvTel;
    EditText pass;

    //Informations bancaires
    Spinner spBankChooser;
    AutoCompleteTextView tvAccountNumber;
    AutoCompleteTextView tvCardNumber;
    AutoCompleteTextView tvCardDate;


    //Information connection
    String phone;
    String password;

    //Information about bank and card
    String bank;
    String cardNumber;
    String account;


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
                int val = checkAnswer();
                if(val==0){
                    Toast.makeText(getApplicationContext(), "Tous les champs doit être remplis", Toast.LENGTH_SHORT).show();
                }
                else {

                }

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

    public int checkAnswer(){

        int val = 0;

        if(tvTel.getText().equals("") || pass.getText().equals("") || tvAccountNumber.getText().equals("") || tvCardNumber.getText().equals("") || spBankChooser.getSelectedItem().equals("choisir votre banque")){
            val = 0;
        }
        else{
            val = 1;
        }

        return  val;
    }



    public void init_views(){

        tvTel = (AutoCompleteTextView) findViewById(R.id.tvTel);
        tvAccountNumber = (AutoCompleteTextView) findViewById(R.id.tvAccountNumber);
        tvCardNumber = (AutoCompleteTextView) findViewById(R.id.tvCardNumber);
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
