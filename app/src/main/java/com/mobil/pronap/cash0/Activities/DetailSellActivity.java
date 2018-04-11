package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
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
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(etProductName.getText().toString(), BarcodeFormat.QR_CODE, 300,300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    i = new Intent(DetailSellActivity.this, QRViewerActivity.class);
                    i.putExtra("qrCode", bitmap);
                    startActivity(i);
                }
                catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });


    }


    public void init_views(){
        etProductDetail = findViewById(R.id.etProductDetail);
        etProductPrice = findViewById(R.id.etProductPrice);
        btnGenerateQR = findViewById(R.id.btnGenarateQR);
        customToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
