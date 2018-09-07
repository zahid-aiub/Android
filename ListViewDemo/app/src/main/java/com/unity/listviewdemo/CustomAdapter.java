package com.unity.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter{

    Context context;
    String[] countryName;
    int[] flags;

    LayoutInflater inflater;

    public CustomAdapter(Context context, String[] countryName, int[] flags) {
        this.context = context;
        this.countryName = countryName;
        this.flags = flags;
    }





    @Override
    public int getCount() {
        return countryName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view==null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sample_layout, viewGroup, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.countryTextView);

        imageView.setImageResource(flags[i]);
        textView.setText(countryName[i]);

        return view;
    }
}
