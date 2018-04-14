package com.mobil.pronap.cash0.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
//import com.mobil.pronap.cash0.Dialog.Fingerprint;
import com.mobil.pronap.cash0.Dialog.Fingerprint;
import com.mobil.pronap.cash0.Fragments.Pinvalidation;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.Utils.SmsValidation;
import com.mobil.pronap.cash0.models.Transaction;
import com.mobil.pronap.cash0.models.User;

import java.util.ArrayList;
import java.util.Date;

public class DetailBuyActivity extends AppCompatActivity {

    TextView tvProductDetail;
    TextView tvProductPrice;
    Button btnValidate;
    Button btnCancel;
    Toolbar customToolbar;
    Intent i;
    FragmentManager fm;
    Pinvalidation pinvalidation;
    AlertDialog alertDialog;

    //Variables to get object
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String listTransaction;
    Gson gson;
    Fingerprint fingerprint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buy);


        fm = getSupportFragmentManager();
        init_views();

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Get information from the scanning qr Code
        //Initialize view with correct info
        pinvalidation = new Pinvalidation();
        fingerprint = new Fingerprint();

        Intent intent = getIntent();
        if(intent!=null){
            String getInformation = intent.getStringExtra("information");
            String[] information = getInformation.split(";");

            tvProductPrice.setText(information[0].toString());
            tvProductDetail.setText(information[1].toString());
        }


        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                fingerprint.show(fm, "FINGERPRINT");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    //fingerprint.show(fm, "FINGERPRINT");
                    //pinvalidation.show(fm, "PIN");
                }else{
                    pinvalidation.show(fm, "PIN");
                }

                //pinvalidation.show(fm, "PIN");

                //Create the transaction object
                Transaction trans = new Transaction();
                trans.setPrix(tvProductPrice.getText().toString());
                trans.setDescription(tvProductDetail.getText().toString());
                trans.setDateTrans(new Date().toString());

                listTransaction = sharedPreferences.getString("listTransaction", null);
                gson = new Gson();
                ArrayList<Transaction> list  = gson.fromJson(listTransaction, ArrayList.class);


                list.add(trans);
                String jsonList = new Gson().toJson(list);
                editor.putString("listTrans",jsonList);

                //Toast.makeText(getApplicationContext(), listTrans.toString(), Toast.LENGTH_SHORT).show();

                editor.commit();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //returnHome();
                fingerprint.show(fm, "FINGERPRINT");
            }
        });


    }

    public void init_views(){

        tvProductDetail = (TextView) findViewById(R.id.tvProductDetail);
        tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        btnValidate = (Button) findViewById(R.id.btnValidate);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        returnHome();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                returnHome();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void returnHome(){
        i = new Intent(DetailBuyActivity.this, DrawerActivity.class);
        startActivity(i);
    }


    public void sendSMS(String phone) {
        String sms = "Please help," +  "location()" + "My Body Diagnostic -\n" + "Heart beat:89BMP\n" + "Oxygen:89% \n" + "Breathing:89 \n" + "Tempeture:89.F \n";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public  void onReceivePin(int pin){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("Validation...");
        alertDialog.show();


        // ********* check if pin correct *****

        // send validation sms
        if(sendBuyConfirmation("37396810", "XXX", tvProductDetail.getText().toString(), tvProductPrice.getText().toString())){
            Toast.makeText(getApplicationContext(),
                    "SMS buyer sent",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),
                    "SMS buyer failed!",
                    Toast.LENGTH_LONG).show();
        }

        if(sendSellConfirmation("42824404", "XXX", tvProductDetail.getText().toString(), tvProductPrice.getText().toString())){
            Toast.makeText(getApplicationContext(),
                    "SMS seller sent",
                    Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getApplicationContext(),
                    "SMS seller failed",
                    Toast.LENGTH_LONG).show();
        }
        alertDialog.dismiss();

    }



    public boolean sendBuyConfirmation(String phone, String p_name, String p_detail, String p_price){
        String sms = "Vous venez d'achetez un produit sur la plateforme Cash 0.\n Nom du produit: "+ p_name +" \n Note: "+ p_detail + " \n Montant: " + p_price +" \n" + "Merci d'utiliser la plateforme Cash 0 \n";
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        }
    }

    public boolean sendSellConfirmation(String phone,String p_name, String p_detail, String p_price){
        String sms = "Vous venez de vendre un produit sur la plateforme Cash 0.\n Nom du produit: "+ p_name +" \n Note: "+ p_detail + " \n Montant: " + p_price +" \n" + "Le montant sera directement debiter sur votre compte. Merci d'utiliser la plateforme Cash 0 \n";
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        }
    }

}
