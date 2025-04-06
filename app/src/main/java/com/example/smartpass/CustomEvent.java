package com.example.smartpass;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class CustomEvent extends BaseAdapter {
    private Context context;
    private ArrayList<String> a, b, c;

    public CustomEvent(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
        this.context = applicationContext;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int getCount() {
        return (a.isEmpty()) ? 1 : a.size();
    }

    @Override
    public Object getItem(int position) {
        return (a.isEmpty()) ? null : a.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
        ImageView im = gridView.findViewById(R.id.event_image);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        if (a.isEmpty()) {
            tv1.setText("No Events");
            tv1.setTextColor(Color.RED);
            tv2.setVisibility(View.GONE);
        } else {
            tv1.setText(a.get(position));
            tv2.setText(b.get(position));

            tv1.setTextColor(Color.BLACK);
            tv2.setTextColor(Color.BLACK);
            tv2.setVisibility(View.VISIBLE);

            // Load image in background thread
            new Thread(() -> {
                try {
                    String ip = sh.getString("ip", "");
                    String imageUrl = "http://" + ip + ":5000/static/uploads/" + c.get(position);

                    URL thumb_u = new URL(imageUrl);
                    Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");

                    im.post(() -> im.setImageDrawable(thumb_d)); // update ImageView on UI thread
                } catch (Exception e) {
                    Log.d("errsssssssssssss", "Image Load Error: " + e);
                }
            }).start();
        }

        return gridView;
    }
}
