package com.leanfarm.vodafoneapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leanfarm.vodafoneapp.DetailsScreen;
import com.leanfarm.vodafoneapp.R;
import com.leanfarm.vodafoneapp.model.Friend;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<Friend> {

    private List<Friend> myFriends;
    private Activity activity;
    private boolean isFromHomeScreen;

    public CustomListViewAdapter(Activity context, int resource, List<Friend> objects) {
        super(context, resource, objects);

        this.activity = context;
        this.myFriends = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.custom_list, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.userNameTV.setText(getItem(position).getName());

        return convertView;
    }


    private static class ViewHolder {
        private TextView userNameTV,timeTV, addressTV;

        public ViewHolder(View v) {
            userNameTV = (TextView) v.findViewById(R.id.name);
        }
    }
}