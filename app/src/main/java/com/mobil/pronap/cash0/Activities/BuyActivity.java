package com.mobil.pronap.cash0.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mobil.pronap.cash0.R;

public class BuyActivity extends AppCompatActivity {


    Button btnScan;
    Intent i;
    Toolbar customToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnScan = (Button) findViewById(R.id.btnScan);
        final Activity activity = this;

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan en cours");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(getApplicationContext(), "No message", Toast.LENGTH_SHORT).show();
                //should pass result to intent
                i = new Intent(BuyActivity.this, DetailBuyActivity.class);
                startActivity(i);

            }
            else {
                Toast.makeText(getApplicationContext(), result.getContents().toString(), Toast.LENGTH_SHORT).show();
                //should pass result to intent
                i = new Intent(BuyActivity.this, DetailBuyActivity.class);
                startActivity(i);
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "No BarCode", Toast.LENGTH_SHORT).show();
            i = new Intent(BuyActivity.this, MainActivity.class);
            startActivity(i);
        }



        super.onActivityResult(requestCode, resultCode, data);
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
}
