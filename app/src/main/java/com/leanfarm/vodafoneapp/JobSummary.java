package com.leanfarm.vodafoneapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.leanfarm.vodafoneapp.adapter.CustomListViewAdapter;
import com.leanfarm.vodafoneapp.adapter.ListViewAdapter;
import com.leanfarm.vodafoneapp.model.Friend;

import java.util.ArrayList;

public class JobSummary extends Activity {

    ArrayList<Friend> friendArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_job_summary);

        ((TextView)findViewById(R.id.titleTV)).setText(getIntent().getExtras().getString("title"));

        ((ImageView)findViewById(R.id.backIconIMG)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        friendArrayList = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.list_item);

        for (int i = 1; i < 20; i++) {

            Friend friend = new Friend("Name " + i);
            friendArrayList.add(friend);

        }

        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.item_listview, friendArrayList);
        listView.setAdapter(adapter);


    }
}
