package com.leanfarm.vodafoneapp.constants;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leanfarm.vodafoneapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by hi on 5/4/2017.
 */

public class CancelJob  {

    public static void cancelJob(final String jobId, final String jobStatus, final Context context) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage("Realy want to cancel job?");
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog1, int which) {
                        dialog1.dismiss();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

                        String[] reasons = {"Customer not picking phone", "Customer not available", "Cancelled by customer", "Address not traceable",
                                "Others"};
                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_cancel_dialog);
                        dialog.setCanceledOnTouchOutside(false);
                        final Spinner reasonSpinner = (Spinner) dialog.findViewById(R.id.reasonSpinner);
                        reasonSpinner.setAdapter(new ArrayAdapter<String>(context, R.layout.custom_spinner, reasons));
                        final EditText reasonET = (EditText) dialog.findViewById(R.id.reasonET);
                        ((TextView) dialog.findViewById(R.id.cancelJobStatusTV)).setText("Status : " + jobStatus);
                        ((TextView) dialog.findViewById(R.id.cancelJobTimeTV)).setText("Time  : " + dateFormat.format(Calendar.getInstance().getTimeInMillis()));

                        reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position == 4) {
                                    reasonET.setVisibility(View.VISIBLE);
                                } else {
                                    reasonET.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        dialog.show();

                        Button declineButton = (Button) dialog.findViewById(R.id.cancelBTN);
                        declineButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        Button okButton = (Button) dialog.findViewById(R.id.okBTN);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (reasonSpinner.getSelectedItem().toString().equalsIgnoreCase("Others")) {
                                    if (Validation.isEditTextViewValid(reasonET.getText().toString())) {
                                        dialog.dismiss();

                                    } else {
                                        Toast.makeText(context, "Please enter reason", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });
        alertDialog.show();

    }
}
