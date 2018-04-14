package com.mobil.pronap.cash0.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
    EditText edtPassword;
    EditText edtCheckPassword;
    EditText edtTel;
    EditText edtName;



    //Informations bancaires
    Spinner spBankChooser;
    AutoCompleteTextView tvAccountNumber;
    AutoCompleteTextView tvCardNumber;
    AutoCompleteTextView tvCardDate;


    //persistence
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Gson gsonUser;


    Button btnRegister;
    Intent i;

    Toolbar customToolbar;

    private boolean isValid = false;
    AlertDialog alertDialog;

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

                alertDialog = new AlertDialog.Builder(SignupActivity.this).create();
                alertDialog.setMessage(getString(R.string.creating_account));
                alertDialog.show();

                if(validationCheck()){
                    //get user info
                    User user = new User();
                    user.setName(edtName.getText().toString());
                    user.setPhone(edtTel.getText().toString());
                    user.setPassword(edtPassword.getText().toString());

                    //get card info
                    Card userCard = new Card();
                    userCard.setRoutingNumberBank("00111011");
                    userCard.setNoCompt(tvAccountNumber.getText().toString());
                    userCard.setUserdId(edtTel.getText().toString());
                    userCard.setCardNumber(tvCardNumber.getText().toString());


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



    public boolean validationCheck() {
        if(!TextUtils.isEmpty(edtName.getText().toString())) {
            if (!TextUtils.isEmpty(edtTel.getText().toString())) {
                if (!TextUtils.isEmpty(edtPassword.getText().toString())) {
                    if (!TextUtils.isEmpty(edtCheckPassword.getText().toString())) {
                        if (!TextUtils.isEmpty(tvAccountNumber.getText().toString())) {
                            if (!TextUtils.isEmpty(tvCardNumber.getText().toString())) {
                                if (edtTel.getText().toString().length() == 8) {
                                    if (edtPassword.getText().toString().length() >= 8) {
                                        if (TextUtils.equals(edtPassword.getText().toString(), edtCheckPassword.getText().toString())) {
                                            if (tvCardNumber.getText().toString().length() == 16) {
                                                isValid = true;
                                            } else {
                                                tvCardNumber.setError(getString(R.string.card_digits_error));
                                            }
                                        } else {
                                            edtCheckPassword.setError(getString(R.string.same_password_error));
                                        }
                                    } else {
                                        edtPassword.setError(getString(R.string.error_invalid_password));
                                    }
                                } else {
                                    edtTel.setError(getString(R.string.incorrect_field));
                                }
                            } else {
                                tvCardNumber.setError(getString(R.string.error_field_required));
                            }
                        } else {
                            tvAccountNumber.setError(getString(R.string.error_field_required));
                        }
                    } else {
                        edtCheckPassword.setError(getString(R.string.error_field_required));
                    }
                } else {
                    edtPassword.setError(getString(R.string.error_field_required));
                }
            } else {
                edtTel.setError(getString(R.string.error_field_required));
            }
        }else{
            edtName.setError(getString(R.string.error_field_required));
        }

        alertDialog.dismiss();
        return isValid;
    }



    public void init_views(){
        edtName = findViewById(R.id.edtName);
        edtTel =  findViewById(R.id.edtTel);
        edtPassword = findViewById(R.id.edtpassword);
        edtCheckPassword = findViewById(R.id.edtCheckpassword);
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
