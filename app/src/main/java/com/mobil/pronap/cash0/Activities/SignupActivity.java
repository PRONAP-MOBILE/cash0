package com.mobil.pronap.cash0.Activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
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
import com.mobil.pronap.cash0.Dialog.NumberVerification;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.Card;
import com.mobil.pronap.cash0.models.User;

import java.util.Random;

import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_FINGERPRINT;

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
    public int verificationNumber;
    private int min = 10000;
    private int max = 90000;
    User user;
    Card userCard;
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int REQUEST_SEND_SMS = 0;
    private static final int REQUEST_SENSOR = 0;
    private static final int REQUEST_FINGERPRINT = 0;
    private static final int REQUEST_WRITE_SMS = 0;


    //persistence
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Gson gsonUser;


    Button btnRegister;
    Intent i;
    FragmentManager fm;
    NumberVerification numberVerification;

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

        fm = getSupportFragmentManager();
        Random r = new Random();
        verificationNumber = r.nextInt(max - min + 1) + min;
        numberVerification = new NumberVerification();
        requestPermission();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(validationCheck()){

                    sendCdeConfirmation(verificationNumber);
                    numberVerification.show(fm, "VERIFICATION");
                    /*
                    //get user info
                    User user = new User();
                    user.setName(edtName.getText().toString());
                    user.setPhone(edtTel.getText().toString());
                    user.setPassword(edtPassword.getText().toString());

                    //get card info
                    Card userCard = new Card();
                    userCard.setRoutingNumberBank("00111011");
                    userCard.setType(spBankChooser.getSelectedItem().toString());
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
                    */
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


    public boolean sendCdeConfirmation(int number){
        String sms = "Code de verification : \n \n" + number + "\n\n Merci d'utiliser Cash 0";
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(edtTel.getText().toString(), null, sms, null, null);
            //Toast.makeText(this, "sent", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "not sent", Toast.LENGTH_SHORT).show();
            e.getMessage();
            return false;
        }
    }

    public void verifyCode(){

        alertDialog = new AlertDialog.Builder(SignupActivity.this).create();
        alertDialog.setMessage(getString(R.string.creating_account));
        alertDialog.show();

        user = new User();
        user.setName(edtName.getText().toString());
        user.setPhone(edtTel.getText().toString());
        user.setPassword(edtPassword.getText().toString());

        //get card info
        userCard = new Card();
        userCard.setRoutingNumberBank("00111011");
        userCard.setType(spBankChooser.getSelectedItem().toString());
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

    public void requestPermission(){
        if(!mayRequestSMS() && !mayRequestWriteSMS()){
            return;
        }

    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(edtCheckPassword, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    private boolean mayRequestSMS() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(edtCheckPassword, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{SEND_SMS}, REQUEST_SEND_SMS);
                        }
                    });
        } else {
            requestPermissions(new String[]{SEND_SMS}, REQUEST_SEND_SMS);
        }
        return false;
    }

    private boolean mayRequestFingerPrint() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(USE_FINGERPRINT)) {
            Snackbar.make(edtCheckPassword, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{USE_FINGERPRINT}, REQUEST_FINGERPRINT);
                        }
                    });
        } else {
            requestPermissions(new String[]{SEND_SMS}, REQUEST_FINGERPRINT);
        }
        return false;
    }

    private boolean mayRequestSensor() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(BODY_SENSORS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(BODY_SENSORS)) {
            Snackbar.make(edtCheckPassword, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{BODY_SENSORS}, REQUEST_SENSOR);
                        }
                    });
        } else {
            requestPermissions(new String[]{BODY_SENSORS}, REQUEST_SENSOR);
        }
        return false;
    }

    private boolean mayRequestWriteSMS() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(RECEIVE_SMS)) {
            Snackbar.make(edtCheckPassword, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{RECEIVE_SMS}, REQUEST_WRITE_SMS);
                        }
                    });
        } else {
            requestPermissions(new String[]{RECEIVE_SMS}, REQUEST_WRITE_SMS);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }



}
