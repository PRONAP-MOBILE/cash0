package com.mobil.pronap.cash0.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mobil.pronap.cash0.R;

public class MainActivity extends AppCompatActivity {

    //ImageButton ibProfil;
    ImageView ivProfil;
    Button btnBuy;
    Button btnSell;
    Intent i;
    Toolbar customToolbar;
    SharedPreferences sharedPreferences;
    Boolean exitApp = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;

        //replace the actionBar with toolbar
        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);
        getSupportActionBar().setTitle("Acceuil");

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
                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan en cours");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
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


    }

    public void init_views(){
        //ibProfil = (ImageButton) findViewById(R.id.ibProfil);
        ivProfil = (ImageView) findViewById(R.id.ivProfil);
        btnBuy = (Button) findViewById(R.id.btnBuy);
        btnSell = (Button) findViewById(R.id.btnSell);
        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        //BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        //myAnim.setInterpolator(interpolator);
        btnBuy.startAnimation(myAnim);
        btnSell.startAnimation(myAnim);

    }


    /* Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }*/


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents()==null){
                //Toast.makeText(getApplicationContext(), "No message", Toast.LENGTH_SHORT).show();
                //should pass result to intent
                finish();
                i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);

            }
            else {
                //should pass result to intent

                //Passing data from the QRcode to the activity for buy
                String information = result.getContents().toString();

                i = new Intent(MainActivity.this, DetailBuyActivity.class);
                i.putExtra("information", information);
                startActivity(i);
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "No BarCode", Toast.LENGTH_SHORT).show();
            i = new Intent(MainActivity.this, DrawerActivity.class);
            startActivity(i);
        }



        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {

            if(exitApp){
                moveTaskToBack(true);
            }else{
                Toast.makeText(this, R.string.quit_message, Toast.LENGTH_SHORT).show();
                exitApp = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exitApp = false;
                    }
                }, 3 * 1000);
            }

    }

}
