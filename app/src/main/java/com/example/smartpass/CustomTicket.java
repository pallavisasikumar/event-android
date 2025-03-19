package com.example.smartpass;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartpass.R;

import java.util.ArrayList;

public class CustomTicket extends BaseAdapter{
    private Context context;

    ArrayList<String> a,b,c,d,e;




    public CustomTicket(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_ticket, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.ticket_name);
        TextView tv2=(TextView)gridView.findViewById(R.id.ticket_dob);
        TextView tv3=(TextView)gridView.findViewById(R.id.ticket_gender);
        TextView tv4=(TextView)gridView.findViewById(R.id.ticket_gov_id);
        TextView tv5=(TextView)gridView.findViewById(R.id.ticket_status);




        tv1.setText(a.get(position));
        tv2.setText(b.get(position));
        tv2.setText(c.get(position));
        tv2.setText(d.get(position));
        tv2.setText(e.get(position));




        return gridView;

    }

}