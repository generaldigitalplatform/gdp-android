package com.leanfarm.vodafoneapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanfarm.vodafoneapp.constants.CancelJob;
import com.leanfarm.vodafoneapp.constants.SignatureView;

public class EndJob extends Activity {

    public SignatureView mSignature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_end_job);

        mSignature = (SignatureView) findViewById(R.id.user_sign);

        ((TextView) findViewById(R.id.cancelJobTV)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelJob.cancelJob("1", "Collected documents", EndJob.this);
            }
        });

        ((ImageView)findViewById(R.id.backIconIMG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((Button)findViewById(R.id.finishBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndJob.this, HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

          ((Button)findViewById(R.id.clearSignatureBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       mSignature.clear();


            }
        });



    }
}
