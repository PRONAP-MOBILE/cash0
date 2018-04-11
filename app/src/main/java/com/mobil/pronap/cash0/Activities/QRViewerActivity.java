package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mobil.pronap.cash0.R;

public class QRViewerActivity extends AppCompatActivity {

    ImageView qrCode;
    Toolbar customToolbar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrviewer);

        customToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        qrCode = findViewById(R.id.ivQRCode);

        Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra("qrCode");

        qrCode.setImageBitmap(bitmap);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                i = new Intent(QRViewerActivity.this, MainActivity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
