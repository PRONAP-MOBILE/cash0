package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobil.pronap.cash0.R;

public class DetailBuyActivity extends AppCompatActivity {

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

        //Get information from the scanning qr Code
        //Initialize view with correct info


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
                //Open dialog fragment for pin
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
        i = new Intent(DetailBuyActivity.this, MainActivity.class);
        startActivity(i);
    }
}
