package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobil.pronap.cash0.R;

public class BuyActivity extends AppCompatActivity {


    Button btnScan;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        btnScan = (Button) findViewById(R.id.btnScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(BuyActivity.this, ScannerActivity.class);
                startActivity(i);
            }
        });


    }
}
