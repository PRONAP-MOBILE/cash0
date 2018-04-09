package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobil.pronap.cash0.R;

public class DetailBuyActivity extends AppCompatActivity {


    TextView tvProductName;
    TextView tvProductDetail;
    TextView tvProductPrice;
    Button btnValidate;
    Button btnCancel;
    Toolbar customToolbar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buy);



        init_views();

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open dialog fragment for pin
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(DetailBuyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });




    }

    public void init_views(){

        tvProductDetail = (TextView) findViewById(R.id.tvProductDetail);
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvProductPrice = (TextView) findViewById(R.id.tvProductPrice);
        btnValidate = (Button) findViewById(R.id.btnValidate);
        btnCancel = (Button) findViewById(R.id.btnCancel);

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
