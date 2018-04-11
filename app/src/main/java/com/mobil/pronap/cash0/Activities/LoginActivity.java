package com.mobil.pronap.cash0.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobil.pronap.cash0.models.User;
import com.mobil.pronap.cash0.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    // UI references.
    private TextInputLayout phone;
    private TextInputLayout pass;
    private EditText phoneUser;
    private EditText passUser;
    private Button login;
    private View mProgressView;
    Toolbar customToolbar;

    //persistence
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    ProgressDialog progressDialog;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize persistence variable
        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("infoUser", null)!=null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else {
            //reference views
            customToolbar = (Toolbar) findViewById(R.id.customToolbar);
            setSupportActionBar(customToolbar);

            // Set up the login form.

            //get input text from user
            phone = (TextInputLayout) findViewById(R.id.phoneNumber);
            pass = (TextInputLayout) findViewById(R.id.password);
            phoneUser = findViewById(R.id.edtphoneNumber);
            passUser = findViewById(R.id.edtpassword);

            login = findViewById(R.id.btLogin);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login(phoneUser.getText().toString(), passUser.getText().toString());

                }
            });

        }


            // mProgressView = findViewById(R.id.login_progress);
    }


    //Login Credentials for user
    private void login(String inputUser, String inputPass) {
        // do not forget to call Backendless.initApp in the app initialization code

        if(inputUser.equals("admin") & inputPass.equals("pass")){
            // user has been logged in
            User user = new User();
            user.setUserName(inputUser);
            gson = new Gson();
            String json = gson.toJson(user);
            editor.putString("infoUser",json);
            editor.apply();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            //progressDialog.dismiss();
        }

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

