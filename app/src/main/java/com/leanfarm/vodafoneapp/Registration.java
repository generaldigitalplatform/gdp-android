package com.leanfarm.vodafoneapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.leanfarm.vodafoneapp.constants.StoredData;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.HashMap;

import io.fabric.sdk.android.Fabric;

public class Registration extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "0cWaH7o9dOl5FNRuuzl4FPDyY";
    private static final String TWITTER_SECRET = "gZvKxQorTDONa8nG12LXlME2HibLV39XmzjDjPgJgnjwLlFV9p";
    private String mPhoneNumer;
    private boolean isMobileNemberVerified;
    TextInputEditText email, fName, lName, mobile;
    TextInputLayout tilEmail, tilFName,tilLName, tilMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (StoredData.getBoolean(Registration.this, "loginStatus")){
            Intent intent = new Intent(Registration.this, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_registration);

            initWidgets();

            ((Button) findViewById(R.id.signUpBTN)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


//                MyApplication.lokiCode(RegistrationActivity.this);
//                MyApplication.copyDatabase(RegistrationActivity.this, "gor_en.db");
                    if (isValidated()) {
                        mPhoneNumer = getMobileNumber();

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Registration.this);
                        alertDialog.setTitle("Verify Your Mobile");
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                authenticateDigits(getMobileNumber());
                            }
                        });
                        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    }
                }
            });
        }

    }

    private void initWidgets() {

        email = (TextInputEditText) findViewById(R.id.emailET);
        fName = (TextInputEditText) findViewById(R.id.firstNameET);
        lName = (TextInputEditText) findViewById(R.id.lastNameET);
        mobile = (TextInputEditText) findViewById(R.id.mobileNumberET);

        tilEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        tilFName = (TextInputLayout) findViewById(R.id.input_layout_first_name);
        tilLName = (TextInputLayout) findViewById(R.id.input_layout_last_name);
        tilMobile = (TextInputLayout) findViewById(R.id.input_layout_mobile_number);

    }

    public String getMobileNumber() {
        return mobile.getText().toString();
    }
    public String getEmail() {
        return email.getText().toString();
    }

    public String getFName() {
        return fName.getText().toString();
    }

    public String getLName() {
        return lName.getText().toString();
    }

    private boolean isValidated() {
        boolean bStatus = true;

        if (getFName().equals("")) {
            bStatus = false;
            tilFName.setError("Please enter first name");
            fName.requestFocus();
        } else if (getLName().equals("")) {
            bStatus = false;
            tilLName.setError("Please enter last name");
            lName.requestFocus();
        }else if (getMobileNumber().equals("")) {
            bStatus = false;
            tilMobile.setError("Please enter mobile number");
            mobile.requestFocus();
        }  else if (getMobileNumber().equals("")) {
            bStatus = false;
            tilMobile.setError("Please enter mobile number");
            mobile.requestFocus();
        } else if (getEmail().equals("")) {
            bStatus = false;
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Please enter valid email");
            email.requestFocus();
        }
        return bStatus;
    }

    public void authenticateDigits(String mobileNumber) {
        AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                .withAuthCallBack(digitsCallback)
                .withEmailCollection(true)
                .withPhoneNumber("+91" + mobileNumber);

        Digits.authenticate(authConfigBuilder.build());
    }

    final AuthCallback digitsCallback = new AuthCallback() {
        @Override
        public void success(DigitsSession session, String phoneNumber) {
            // TODO: associate the session userID with your user model
            Toast.makeText(Registration.this, "Authentication successful for "
                    + phoneNumber, Toast.LENGTH_LONG).show();
            mPhoneNumer = phoneNumber;
            isMobileNemberVerified = true;
            StoredData.saveBoolean(Registration.this, "loginStatus", true);
            Intent intent = new Intent(Registration.this, HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }

        @Override
        public void failure(DigitsException exception) {
            Log.d("Digits", "Sign in with Digits failure", exception);
        }
    };
}
