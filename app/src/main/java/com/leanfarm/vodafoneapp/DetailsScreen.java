package com.leanfarm.vodafoneapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanfarm.vodafoneapp.constants.CancelJob;

public class DetailsScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details_screen);

        ((ImageView)findViewById(R.id.backIconIMG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.cancelJobTV)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelJob.cancelJob("1", "Job Started", DetailsScreen.this);
            }
        });
        ((Button)findViewById(R.id.reachedCustomerLocationBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsScreen.this, DocumentCollection.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


}
