package com.mobil.pronap.cash0.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.Card;
import com.mobil.pronap.cash0.models.User;

public class SignupActivity extends AppCompatActivity {


    //Informations personnelles
    EditText tvTel;
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

    //persistence
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Gson gsonUser;


    Button btnRegister;
    Intent i;

    Toolbar customToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init_views();

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = checkAnswer();
                if(val==0){
                    Toast.makeText(getApplicationContext(), "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show();
                }
                else {

                    //get user info
                    User user = new User();
                    user.setPhone(tvTel.getText().toString());
                    user.setPassword(pass.getText().toString());

                    //get card info
                    Card userCard = new Card();
                    userCard.setRoutingNumberBank(00111011);
                    userCard.setNoCompt(Integer.parseInt(tvAccountNumber.getText().toString()));
                    userCard.setUserdId(tvTel.getText().toString());
                    userCard.setCardNumber(Integer.parseInt(tvCardNumber.getText().toString()));


                    gsonUser = new Gson();
                    String jsonUser = gsonUser.toJson(user);
                    editor.putString("userRegister",jsonUser);

                    String jsonCard = gsonUser.toJson(userCard);
                    editor.putString("cardRegister",jsonCard);
                    editor.apply();

                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(i);
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

        init_views();

        int val = 0;

        if(tvTel.getText().toString().equals("") || pass.getText().toString().equals("") || tvAccountNumber.getText().toString().equals("") || tvCardNumber.getText().equals("") || spBankChooser.getSelectedItem().equals("Choisir banque")){
            val = 0;
        }
        else{
            val = 1;
        }

        return  val;
    }



    public void init_views(){

        tvTel =  findViewById(R.id.edtTel);
        pass = findViewById(R.id.edtpassword);
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
