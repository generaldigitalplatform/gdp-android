package com.leanfarm.vodafoneapp.constants;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.leanfarm.vodafoneapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by apple on 07/07/15.
 */
public class Validation {
    final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public static boolean isUserNameValid(CharSequence username) {
        if (TextUtils.isEmpty(username)) {
            return false;
        }
        return true;
    }


    public static boolean isMobileNumberValid(CharSequence password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        } else if (password.length() < 10) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordCompareValid(CharSequence password, CharSequence confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmailValid(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public static boolean isEditTextViewValid(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        return true;
    }

    public static int intergerDefaultvalid(String text) {


        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        if (text == "") {
            return 0;
        }
        if (text.equalsIgnoreCase("Empty")){
            return 0;
        }
        return Integer.parseInt(text);
    }

    public static int intergerDefaultvalidNonZero(String text) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        if (text == "") {
            return 0;
        }
        if (Integer.parseInt(text) == 0) {
            return 0;
        }
        return Integer.parseInt(text);
    }

    public static String stringDefaultvalid(String text) {
        if (TextUtils.isEmpty(text)) {
            return "Empty";
        }
        return text;
    }

    public static String stringRemoveEmpty(String text) {
        if (text.equalsIgnoreCase("Empty")) {
            return "";
        }
        return text;
    }

    public static String stringRemoveEmptyAndZero(String text) {
        if (text.equalsIgnoreCase("Empty")) {
            return "";
        }
        if (text.equalsIgnoreCase("0")) {
            return "";
        }
        return text;
    }

    public static String booleanToString(String text) {
        if (text.equalsIgnoreCase("0")) {
            return "No";
        } else {
            return "Yes";
        }
    }

    public static int statusToInt(String text) {
        if (text == null) {
            return 2;
        }
        if (text.equalsIgnoreCase("")) {
            return 2;
        }
        if (text.equalsIgnoreCase("Job Started")) {
            return 3;
        } else if (text.equalsIgnoreCase("Reached Customer")) {
            return 4;
        } else if (text.equalsIgnoreCase("Pickup Completed")) {
            return 5;
        } else if (text.equalsIgnoreCase("Job Completed")) {
            return 6;
        } else if (text.equalsIgnoreCase("Job Cancelled")) {
            return 7;
        }
        return 2;
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    public static void displayLocationAlert(final Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage("Please enable GPS");
        alertDialog.setCanceledOnTouchOutside(false);

//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);

                    }
                });
        alertDialog.show();
    }

    public static boolean validateDate(String currentDate, String selectedDate) {

        boolean valid = false;

        try {

            Date date1 = sdf.parse(currentDate);
            Date date2 = sdf.parse(selectedDate);

            System.out.println(sdf.format(date1));
            System.out.println(sdf.format(date2));

            if (date1.after(date2)) {
//                System.out.println("Date1 is after Date2");
                valid = false;
            } else if (date1.before(date2)) {
//                System.out.println("Date1 is before Date2");
                valid = true;
            } else if (date1.equals(date2)) {
//                System.out.println("Date1 is equal Date2");
                valid = true;
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return valid;
    }


    public static boolean isFutureDate(String currentDate, String selectedDate) {

        boolean valid = false;

        try {

            Date date1 = sdf.parse(currentDate);
            Date date2 = sdf.parse(selectedDate);

            System.out.println(sdf.format(date1));
            System.out.println(sdf.format(date2));

            if (date1.after(date2)) {
//                System.out.println("Date1 is after Date2");
                valid = false;
            } else if (date1.before(date2)) {
//                System.out.println("Date1 is before Date2");
                valid = true;
            } else if (date1.equals(date2)) {
//                System.out.println("Date1 is equal Date2");
                valid = false;
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return valid;
    }

    public static boolean timeValidation(String time, String date) {

        if (date.equalsIgnoreCase(sdf.format(Calendar.getInstance().getTimeInMillis()))) {
            String[] timeArry = time.split(" ");
            int timeVal = 8;
            if (Integer.parseInt(timeArry[0]) == 12) {
                timeVal = Integer.parseInt(timeArry[0]);
            } else if (timeArry[1].equalsIgnoreCase("PM")) {
                timeVal = Integer.parseInt(timeArry[0]) + 12;
            } else {
                timeVal = Integer.parseInt(timeArry[0]);
            }
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH", Locale.ENGLISH);
            int currentTime = Integer.parseInt(timeFormat.format(Calendar.getInstance().getTimeInMillis()));
            if (timeVal > currentTime) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static String getCleaningScore(String s) {
        if (s.equalsIgnoreCase("Good")){
            return "1";
        }else if(s.equalsIgnoreCase("Ok")){
            return "2";
        }else if(s.equalsIgnoreCase("Bad")){
            return "3";
        }else{
            return "1";
        }
    }
}
