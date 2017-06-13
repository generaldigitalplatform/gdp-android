package com.leanfarm.vodafoneapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leanfarm.vodafoneapp.R;


/**
 * Created by hi on 9/22/2016.
 */
public class Slider_Adapter extends BaseAdapter {

    Context context;
    int[] sliderImages;
    String[] sliderNames;
    LayoutInflater inflater;

    public Slider_Adapter(Context context, int[] sliderImages, String[] sliderNames)
    {
        this.context = context;
        this.sliderImages = sliderImages;
        this.sliderNames = sliderNames;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sliderImages.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_slider_menu,null);
        }
        ((ImageView)convertView.findViewById(R.id.sliderIMG)).setImageResource(sliderImages[position]);
        ((TextView)convertView.findViewById(R.id.sliderNameTV)).setText(sliderNames[position]);

        return convertView;
    }
}
