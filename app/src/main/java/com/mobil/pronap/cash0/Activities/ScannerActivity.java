package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobil.pronap.cash0.R;

public class ScannerActivity extends AppCompatActivity {


    Button btnCamera;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        btnCamera = (Button) findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(ScannerActivity.this, DetailBuyActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
