package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


class ListViewItem {
    private String text;
    
    public ListViewItem(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}

public class ListViewAdapter extends ArrayAdapter<ListViewItem> {
    private static final int layoutResourceId = android.R.layout.simple_list_item_1;
    private final LayoutInflater inflater;

    public ListViewAdapter(Context context) {
        super(context, layoutResourceId);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(layoutResourceId, parent, false);
        ListViewItem selectedItem = getItem(position);
        TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
        textView.setText(selectedItem.toString());
        return rowView;
    }
    
    public void add(String text) {
        text = text.replace('\n', ' ').trim();
        if (!text.equals("")) {
            add(new ListViewItem(text));
        }
    }
}