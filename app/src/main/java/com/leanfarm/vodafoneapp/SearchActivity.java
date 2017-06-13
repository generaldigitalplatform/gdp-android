package com.leanfarm.vodafoneapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.leanfarm.vodafoneapp.adapter.CustomListViewAdapter;
import com.leanfarm.vodafoneapp.adapter.ListViewAdapter;
import com.leanfarm.vodafoneapp.model.Friend;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    RadioGroup radioGroup;
    EditText phoneNumberET, nameET;
    ArrayList<Friend> friendArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        phoneNumberET = (EditText) findViewById(R.id.phoneNumberET);
        nameET = (EditText) findViewById(R.id.nameET);

        phoneNumberET.setEnabled(false);
        ((ImageView)findViewById(R.id.backIconIMG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.searchRG);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                // Add logic here
                switch (index) {
                    case 0: // first button
                       nameET.setEnabled(true);
                        phoneNumberET.setEnabled(false);
                        break;
                    case 1: // secondbutton
                        nameET.setEnabled(false);
                        phoneNumberET.setEnabled(true);
                        break;
                }
            }
        });


        ((ImageView)findViewById(R.id.backIconIMG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        friendArrayList = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.jobsListView);

        for (int i = 1; i < 20; i++) {

            Friend friend = new Friend("Name " + i);
            friendArrayList.add(friend);

        }

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.item_listview, friendArrayList);
        listView.setAdapter(adapter);

    }
}
