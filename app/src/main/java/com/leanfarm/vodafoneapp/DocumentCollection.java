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

public class DocumentCollection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_document_collection);

        ((TextView) findViewById(R.id.cancelJobTV)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelJob.cancelJob("1", "Reached Customer Location", DocumentCollection.this);
            }
        });

        ((ImageView)findViewById(R.id.backIconIMG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((Button)findViewById(R.id.nextBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocumentCollection.this, EndJob.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
