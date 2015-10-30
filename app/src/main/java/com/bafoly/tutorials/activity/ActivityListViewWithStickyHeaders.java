package com.bafoly.tutorials.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bafoly.tutorials.R;
import com.bafoly.tutorials.adapter.StickyListViewAdapter;
import com.bafoly.tutorials.view.ListViewStickyHeader;

import java.util.ArrayList;
import java.util.List;

public class ActivityListViewWithStickyHeaders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_with_sticky_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ListViewStickyHeader");

        ListViewStickyHeader listView = (ListViewStickyHeader) findViewById(R.id.listview);
        List<String> elements = new ArrayList<String>();
        elements.add("1");
        elements.add("2");
        elements.add("3");
        elements.add("4");
        elements.add("5");
        elements.add("6");
        listView.setAdapter(new StickyListViewAdapter(this, R.layout.row_list, elements));
    }
}
