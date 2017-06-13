package com.leanfarm.vodafoneapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leanfarm.vodafoneapp.adapter.ListViewAdapter;
import com.leanfarm.vodafoneapp.adapter.Slider_Adapter;
import com.leanfarm.vodafoneapp.constants.StoredData;
import com.leanfarm.vodafoneapp.model.Friend;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Friend> friendArrayList;
    int[] sliderImage = {R.drawable.slider_knob_on, R.drawable.slider_knob_on, R.drawable.slider_knob_on, R.drawable.slider_knob_on, R.drawable.slider_knob_on};
    String[] sliderNames = {"Call Supervisor", "Search Jobs","Notifications", "About", "Help"};
    ListView slideMenuList, jobsListView;
    LinearLayout pendingJobsLL, completedJobsLL, cancelJobsLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        slideMenuList = (ListView) navigationView.findViewById(R.id.slideMenuList);

        slideMenuList.setAdapter(new Slider_Adapter(HomeScreen.this, sliderImage, sliderNames));

        friendArrayList = new ArrayList<>();
        jobsListView = (ListView) findViewById(R.id.list_item);

        for (int i = 1; i < 20; i++) {

            Friend friend = new Friend("Name " + i);
            friendArrayList.add(friend);

        }

        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.item_listview, friendArrayList, true);
        jobsListView.setAdapter(adapter);

       /* jobsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(HomeScreen.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_userdetails_dialog);
                dialog.setCanceledOnTouchOutside(false);
            *//*    ((TextView) dialog.findViewById(R.id.customerNameTV)).setText(job_models.get(position1).getCustomerName());
                ((TextView) dialog.findViewById(R.id.customerAddressTV)).setText(job_models.get(position1).getAddress());
                ((TextView) dialog.findViewById(R.id.phoneNumberTV)).setText(job_models.get(position1).getPhoneNumber1());
                ((TextView) dialog.findViewById(R.id.regNoTV)).setText(job_models.get(position1).getVehicleRegNo());
*//*
                ((ImageView) dialog.findViewById(R.id.callIMG)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:9738067543" ));
                        startActivity(callIntent);
                    }
                });

                ((ImageView) dialog.findViewById(R.id.okIMG)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ((ImageView) dialog.findViewById(R.id.goDetailsIMG)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                       Intent intent = new Intent(HomeScreen.this, DetailsScreen.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        cancelJobsLL = (LinearLayout) navigationView.findViewById(R.id.cancelJobLL);
        pendingJobsLL = (LinearLayout) navigationView.findViewById(R.id.pendingJobsLL);
        completedJobsLL = (LinearLayout) navigationView.findViewById(R.id.completedJobsLL);


        slideMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + "9738067543"));
                    startActivity(callIntent);
                } else if (position == 4) {
                    Intent intent = new Intent(HomeScreen.this, HelpScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(HomeScreen.this, AboutUs.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(HomeScreen.this, SearchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        completedJobsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, JobSummary.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("title", "Completed Jobs");
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        pendingJobsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, JobSummary.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("title", "Pending Jobs");
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        cancelJobsLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, JobSummary.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("title", "Cancelled Jobs");
                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        ((LinearLayout) findViewById(R.id.logoutLL)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(HomeScreen.this).create();
                alertDialog.setMessage("Do you really want to logout?");
                alertDialog.setCanceledOnTouchOutside(false);

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                StoredData.saveBoolean(HomeScreen.this, "loginStatus", false);
                                finishAffinity();
                                Intent intent = new Intent(HomeScreen.this, Registration.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });


                alertDialog.show();
            }
        });

        ((Button)findViewById(R.id.startJobBTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, DetailsScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
