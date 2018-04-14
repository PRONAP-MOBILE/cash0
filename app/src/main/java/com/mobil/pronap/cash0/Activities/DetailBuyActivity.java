package com.mobil.pronap.cash0.Activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
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
import com.mobil.pronap.cash0.models.Card;
import com.mobil.pronap.cash0.models.Transaction;
import com.mobil.pronap.cash0.models.User;

import java.util.ArrayList;
import java.util.Date;

import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_FINGERPRINT;

public class DetailBuyActivity extends AppCompatActivity {

    TextView tvProductDetail;
    TextView tvProductPrice;
    TextView tvInfoSell;
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
    private String[] information;
    private String getInformation;
    private String userInfo;
    private String cardInfo;
    private User user;
    private Card card;

    private static final int REQUEST_READ_CONTACTS = 0;
    private static final int REQUEST_SEND_SMS = 0;
    private static final int REQUEST_SENSOR = 0;
    private static final int REQUEST_FINGERPRINT = 0;
    private static final int REQUEST_WRITE_SMS = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buy);


        fm = getSupportFragmentManager();
        init_views();

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();


        userInfo = sharedPreferences.getString("userRegister", null);
        cardInfo = sharedPreferences.getString("cardRegister", null);


        user = gson.fromJson(userInfo, User.class);
        card = gson.fromJson(cardInfo, Card.class);

        //Get information from the scanning qr Code
        //Initialize view with correct info
        pinvalidation = new Pinvalidation();
        fingerprint = new Fingerprint();

        Intent intent = getIntent();
        if(intent!=null){
            getInformation = intent.getStringExtra("information");
            information = getInformation.split(";");

            tvProductPrice.setText(information[0].toString());
            tvProductDetail.setText(information[1].toString());
            tvInfoSell.setText("@Vendeur: "+information[3].toString());
        }

        requestPermission();


        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                fingerprint.show(fm, "FINGERPRINT");


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
                editor.putString("listTransaction",jsonList);

                //Toast.makeText(getApplicationContext(), listTrans.toString(), Toast.LENGTH_SHORT).show();

                editor.commit();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();

            }
        });


    }

    public void init_views(){

        tvProductDetail = (TextView) findViewById(R.id.tvProductDetail);
        tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        tvInfoSell = (TextView) findViewById(R.id.tvVendeurInfo);
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




    public  void onReceivePin(String pin){
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("Validation...");
        alertDialog.show();


        getInformation = getIntent().getStringExtra("information");
        information = getInformation.split(";");

        // ********* check if pin correct *****

        // send validation sms

        if(sendBRHConfirmation(card.getNoCompt(), card.getCardNumber(), pin, card.getRoutingNumberBank(), information[2].toString(), information[4].toString(), null, information[0].toString())){

        }else{
            Toast.makeText(getApplicationContext(),
                    "BRH sms sent",
                    Toast.LENGTH_LONG).show();
        }

        if(sendBuyConfirmation(user.getPhone(), "XXX", tvProductDetail.getText().toString(), tvProductPrice.getText().toString())){
            Toast.makeText(getApplicationContext(),
                    "SMS buyer sent",
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),
                    "SMS buyer failed!",
                    Toast.LENGTH_LONG).show();
        }

        if(sendSellConfirmation(information[2].toString(), "XXX", tvProductDetail.getText().toString(), tvProductPrice.getText().toString())){
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
            e.getMessage();
            return false;
        }
    }


    public boolean sendBRHConfirmation(String buyer_accountNumber, String buyer_cardNumber, String buyer_pin, String buyer_routingNumber,
                                       String seller_accountNumber, String seller_cardNumber, String seller_routingNumber, String price){
        String sms = "Transfert d'argent par Cash 0 .\n\n Compte a crediter  \n No compte: "
                + buyer_accountNumber +" \n No Carte: "+ buyer_cardNumber+ " \n PIN: " +
                buyer_pin +"\n routing number: " + buyer_routingNumber +  "  \n\n Compte a debiter \n No compte: " +  seller_accountNumber +
                "\n No de carte: " + seller_cardNumber + "\n routing number: " + seller_routingNumber +
                "\n\n Montant de la transaction: " + price;
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage("37396810", null, sms, null, null);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public void requestPermission(){
        if(!mayRequestFingerPrint()){
            return;
        }

    }


    private boolean mayRequestFingerPrint() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(USE_FINGERPRINT)) {
            Snackbar.make(tvProductDetail, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
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

}
