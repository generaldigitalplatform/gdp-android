package com.leanfarm.vodafoneapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leanfarm.vodafoneapp.DetailsScreen;
import com.leanfarm.vodafoneapp.HomeScreen;
import com.leanfarm.vodafoneapp.R;
import com.leanfarm.vodafoneapp.model.Friend;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Friend> {

    private List<Friend> myFriends;
    private Activity activity;
    private int selectedPosition = -1;
    private boolean isFromHomeScreen;

    public ListViewAdapter(Activity context, int resource, List<Friend> objects, boolean isFromHomeScreen) {
        super(context, resource, objects);

        this.activity = context;
        this.myFriends = objects;
        this.isFromHomeScreen = isFromHomeScreen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.checkBox.setTag(position); // This line is important.

        if (isFromHomeScreen){
            holder.checkBox.setVisibility(View.VISIBLE);
        }else {
            holder.checkBox.setVisibility(View.INVISIBLE);
        }

        holder.userNameTV.setText(getItem(position).getName());
        if (position == selectedPosition) {
            holder.checkBox.setChecked(true);

            holder.frontRL.setVisibility(View.GONE);
            holder.backRL.setVisibility(View.VISIBLE);
            holder.userNameTV1.setText(getItem(position).getName());

            holder.callIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:9738067543" ));
                    getContext().startActivity(callIntent);
                }
            });

            holder.okIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.goDetailsIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), DetailsScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getContext().startActivity(intent);
                }
            });

        } else{
            holder.checkBox.setChecked(false);
            holder.frontRL.setVisibility(View.VISIBLE);
            holder.backRL.setVisibility(View.GONE);
        }

        holder.checkBox.setOnClickListener(onStateChangedListener(holder.checkBox, position));

        return convertView;
    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selectedPosition = position;
                } else {
                    selectedPosition = -1;
                }
                notifyDataSetChanged();
            }
        };
    }

    private static class ViewHolder {
        private TextView userNameTV,timeTV, addressTV,userNameTV1,timeTV1, addressTV1;
        private CheckBox checkBox;
        private RelativeLayout backRL, frontRL;
        ImageView goDetailsIMG, okIMG, callIMG;

        public ViewHolder(View v) {
            checkBox = (CheckBox) v.findViewById(R.id.check);
            userNameTV = (TextView) v.findViewById(R.id.name);
            userNameTV1 = (TextView) v.findViewById(R.id.name1);
            backRL = (RelativeLayout) v.findViewById(R.id.backRL);
            frontRL = (RelativeLayout) v.findViewById(R.id.frontRL);
            goDetailsIMG = (ImageView) v.findViewById(R.id.goDetailsIMG);
            okIMG = (ImageView) v.findViewById(R.id.okIMG);
            callIMG = (ImageView) v.findViewById(R.id.callIMG);

        }
    }
}