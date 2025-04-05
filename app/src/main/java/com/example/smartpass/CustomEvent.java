package com.example.smartpass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomEvent extends BaseAdapter {
    private Context context;
    private ArrayList<String> a, b;

    public CustomEvent(Context applicationContext, ArrayList<String> a, ArrayList<String> b) {
        this.context = applicationContext;
        this.a = a;
        this.b = b;
    }

    @Override
    public int getCount() {
        // If the list is empty, return 1 to show "No Events" message
        return (a.isEmpty()) ? 1 : a.size();
    }

    @Override
    public Object getItem(int position) {
        return (a.isEmpty()) ? null : a.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // Return position instead of 0
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.activity_custom_event, null);
        } else {
            gridView = convertView;
        }

        TextView tv1 = gridView.findViewById(R.id.event_name);
        TextView tv2 = gridView.findViewById(R.id.event_date);

        if (a.isEmpty()) {
            // Show "No Events" message in the first row
            tv1.setText("No Events");
            tv1.setTextColor(Color.RED);

            // Hide event date since there's no event
            tv2.setVisibility(View.GONE);
        } else {
            // Populate event data normally
            tv1.setText(a.get(position));
            tv2.setText(b.get(position));

            tv1.setTextColor(Color.BLACK);
            tv2.setTextColor(Color.BLACK);

            // Ensure visibility in case it was hidden
            tv2.setVisibility(View.VISIBLE);
        }

        return gridView;
    }
}
