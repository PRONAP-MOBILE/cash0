package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobil.pronap.cash0.R;

public class DetailSellActivity extends AppCompatActivity {

    EditText etProductName;
    EditText etProductDetail;
    EditText etProductPrice;
    Button btnGenerateQR;
    Toolbar customToolbar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sell);
        init_views();
        btnGenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //generate QR code
                i = new Intent(DetailSellActivity.this, QRViewerActivity.class);
                startActivity(i);
            }
        });


    }


    public void init_views(){
        etProductName = (EditText) findViewById(R.id.etProductName);
        etProductDetail = (EditText) findViewById(R.id.etProductDetail);
        etProductPrice = (EditText) findViewById(R.id.etProductPrice);
        btnGenerateQR = (Button) findViewById(R.id.btnGenarateQR);
        customToolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
