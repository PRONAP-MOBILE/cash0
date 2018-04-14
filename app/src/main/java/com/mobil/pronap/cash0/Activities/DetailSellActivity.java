package com.mobil.pronap.cash0.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.Card;
import com.mobil.pronap.cash0.models.User;

public class DetailSellActivity extends AppCompatActivity {


    EditText etProductDetail;
    EditText etProductPrice;
    Button btnGenerateQR;
    Toolbar customToolbar;
    Intent i;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    private String userInfo;
    private String cardInfo;
    private User user;
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sell);
        init_views();

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();


        userInfo = sharedPreferences.getString("userRegister", null);
        cardInfo = sharedPreferences.getString("cardRegister", null);


        user = gson.fromJson(userInfo, User.class);
        card = gson.fromJson(cardInfo, Card.class);

        btnGenerateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //generate QR code if input objects are not empty
                if(!TextUtils.isEmpty(etProductDetail.getText().toString()) &&
                        !TextUtils.isEmpty(etProductPrice.getText().toString())){

                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try{

                        String transInfo = etProductPrice.getText().toString() + ";" + etProductDetail.getText().toString() + ";" + user.getPhone().toString() + ";" + user.getName().toString() + ";" + card.getCardNumber().toString() + ";" + card.getNoCompt().toString();

                        BitMatrix bitMatrix = multiFormatWriter.encode(transInfo, BarcodeFormat.QR_CODE, 300,300);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);




                        i = new Intent(DetailSellActivity.this, QRViewerActivity.class);
                        i.putExtra("qrCode", bitmap);
                        startActivity(i);
                    }
                    catch (WriterException e){
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(DetailSellActivity.this, "Veillez remplir tous les champs", Toast.LENGTH_SHORT).show();
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
        this.finish();
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
