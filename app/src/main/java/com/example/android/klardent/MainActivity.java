package com.example.android.klardent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton b1;
    ImageButton b2;
    ImageButton b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (ImageButton) findViewById(R.id.btn_sendEmail);
        b1.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact@klardent.ro"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "query");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }


        });
        b2 = (ImageButton) findViewById(R.id.btn_makeCall);
        b2.setOnClickListener(new OnClickListener() {
            @Override
            @RequiresPermission(Manifest.permission.CALL_PHONE)
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:072000000"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        b3 = (ImageButton) findViewById(R.id.btn_location);
        b3.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri
                        .parse("geo:37.827500,-122.481670"));
                startActivity(intent);

            }
        });
    }
}