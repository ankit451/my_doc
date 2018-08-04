package com.example.android.pokedr;

/**
 * Created by Siddhant Choudhary on 22-01-2017.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class ListViewCustomAdapter extends BaseAdapter
{
    public String dates[];
    public String illness[];
    public String prescriptions[];
    public Activity context;


    public ListViewCustomAdapter(Activity context,String[] dates, String[] illness,String[] prescriptions) {

        super();

        this.context = context;
        this.dates=dates;
        this.illness=illness;;
        this.prescriptions=prescriptions;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dates.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {

        TextView txtViewDate;
        TextView txtViewIllness;
        TextView txtViewPrescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_items_album, null);


            holder.txtViewDate = (TextView) convertView.findViewById(R.id.medList1);
            holder.txtViewIllness = (TextView) convertView.findViewById(R.id.medList2);
            holder.txtViewPrescription=(TextView) convertView.findViewById(R.id.medList3);
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();



        holder.txtViewDate.setText(dates[position]);
        holder.txtViewIllness.setText(illness[position]);
        holder.txtViewPrescription.setText(prescriptions[position]);

        return convertView;
    }

}
