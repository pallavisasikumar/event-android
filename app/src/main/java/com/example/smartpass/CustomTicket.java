package com.example.smartpass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomTicket extends BaseAdapter {
    private Context context;
    private ArrayList<String> a, b, c, d;

    public CustomTicket(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d) {
        this.context = applicationContext;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public int getCount() {
        // If list is empty, return 1 to show "No Tickets Booked" message.
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
            gridView = inflater.inflate(R.layout.activity_custom_ticket, null);
        } else {
            gridView = convertView;
        }

        TextView tv1 = gridView.findViewById(R.id.ticket_name);
        TextView tv2 = gridView.findViewById(R.id.ticket_dob);
        TextView tv3 = gridView.findViewById(R.id.ticket_gender);
        TextView tv4 = gridView.findViewById(R.id.ticket_gov_id);

        if (a.isEmpty()) {
            // Show "No Tickets Booked" message in the first row
            tv1.setText("No Tickets Booked");
            tv1.setTextColor(Color.RED);

            // Hide other TextViews
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            tv4.setVisibility(View.GONE);
        } else {
            // Populate data normally
            tv1.setText("Name: " + a.get(position));
            tv2.setText("Dob: " + b.get(position));
            tv3.setText("Gender: " + c.get(position));
            tv4.setText("Gov ID: " + d.get(position));



            // Make all TextViews visible
            tv2.setVisibility(View.VISIBLE);
            tv3.setVisibility(View.VISIBLE);
            tv4.setVisibility(View.VISIBLE);
        }

        return gridView;
    }
}
