package com.mobil.pronap.cash0.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.Transaction;

import java.util.ArrayList;

/**
 * Created by jetro on 4/13/18.
 */

public class ArrayAdapterTransaction extends ArrayAdapter<Transaction> {

    //constructor
    public ArrayAdapterTransaction(Context context, ArrayList<Transaction> list)
    {
        super(context, android.R.layout.simple_list_item_1, list);
    }

    //custom view to populate data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Transaction trans = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_transaction, parent, false);
        }

        //Find views
        TextView desc = (TextView) convertView.findViewById(R.id.tvReportDescription);
        TextView prix = (TextView) convertView.findViewById(R.id.tvReportPrice);
        TextView date = convertView.findViewById(R.id.tvReportDate);



        //populate data
        desc.setText(trans.getDescription().toString());
        prix.setText(trans.getPrix().toString());
        date.setText(trans.getDateTrans().toString());


        return convertView;
    }
}


