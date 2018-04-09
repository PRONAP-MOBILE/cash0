package com.mobil.pronap.cash0.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mobil.pronap.cash0.R;

public class QRViewerActivity extends AppCompatActivity {


    ImageButton ibHome;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrviewer);
        ibHome = (ImageButton) findViewById(R.id.ibHome);

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //return home
                i = new Intent(QRViewerActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
