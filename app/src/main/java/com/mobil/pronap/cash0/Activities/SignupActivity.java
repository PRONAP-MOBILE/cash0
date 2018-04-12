package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
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

import com.mobil.pronap.cash0.R;

public class SignupActivity extends AppCompatActivity {


    //Informations personnelles

    EditText edtTel;
    EditText edtpassword;
    EditText edtCheckpassword;


    //Informations bancaires
    Spinner spBankChooser;
    AutoCompleteTextView tvAccountNumber;
    AutoCompleteTextView tvCardNumber;

    Button btnRegister;
    Intent i;
    private boolean acceptable = false;
    Toolbar customToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init_views();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkValidity()) {
                    i = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });


    }

    public boolean checkValidity() {

        if (!TextUtils.isEmpty(edtTel.getText().toString())) {

            if (!TextUtils.isEmpty(edtpassword.getText().toString())) {
                if (!TextUtils.isEmpty(edtCheckpassword.getText().toString())) {
                    if (!TextUtils.isEmpty(tvAccountNumber.getText().toString())) {
                        if (!TextUtils.isEmpty(tvCardNumber.getText().toString())) {
                            if (!TextUtils.equals(edtpassword.getText().toString(), edtCheckpassword.getText().toString())) {
                                acceptable = true;
                            } else {
                                edtCheckpassword.setError("Vos mots de passe ne correspondent pas");
                            }
                        } else {
                            tvCardNumber.setError("Champs requis");
                        }
                    } else {
                        tvAccountNumber.setError("Champs requis");
                    }
                } else {
                    edtCheckpassword.setError("Champs requis");
                }
            } else {
                edtpassword.setError("Champs requis");
            }

        } else {
            edtTel.setError("Champs requis");
        }

        return acceptable;

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


    public void init_views() {
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        edtCheckpassword = (EditText) findViewById(R.id.edtCheckpassword);
        edtTel = (AutoCompleteTextView) findViewById(R.id.edtTel);
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
