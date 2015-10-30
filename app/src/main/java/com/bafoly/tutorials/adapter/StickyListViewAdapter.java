package com.bafoly.tutorials.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bafoly.tutorials.R;
import com.bafoly.tutorials.util.Logger;

import java.util.List;

public class StickyListViewAdapter extends ArrayAdapter<String>{

    Context context;
    int resource;
    List<String> elements;
    LayoutInflater inflater;

    Row row;

    public StickyListViewAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.elements = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(resource, parent, false);
        TextView textView = (TextView) convertView.findViewById(R.id.title_text);

        textView.setText("Header "+elements.get(position));

        return convertView;
    }

    class Row{
        TextView t;
        LinearLayout l;
    }


}
