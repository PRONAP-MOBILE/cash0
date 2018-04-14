package com.mobil.pronap.cash0.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobil.pronap.cash0.Adapters.ArrayAdapterTransaction;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.Transaction;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jetro on 4/9/18.
 */

public class UserTransactions extends Fragment {

    ArrayList<Transaction> listTransaction;
    ArrayAdapterTransaction adapterTransaction;
    ListView lvTransaction;

    //Variables to get object
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String retrieveList;
    Gson gson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.user_transaction, container, false);

        lvTransaction = v.findViewById(R.id.lvTransactions);

        sharedPreferences = getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        retrieveData();

       // Toast.makeText(getContext(), retrieveData().toString(), Toast.LENGTH_SHORT).show();

        return v;

    }


    private void retrieveData() {

        listTransaction = new ArrayList<>();
        adapterTransaction = new ArrayAdapterTransaction(getActivity(), listTransaction);
        lvTransaction.setAdapter(adapterTransaction);

        retrieveList = sharedPreferences.getString("listTransaction", null);
        gson = new Gson();

        Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
        List<Transaction> finaList = new Gson().fromJson(retrieveList, listType);


        for(int i = 0; i < finaList.size(); i++){
            Transaction trans = new Transaction();

            trans.setPrix(finaList.get(i).getPrix());
            trans.setDescription(finaList.get(i).getDescription());
            trans.setDateTrans(finaList.get(i).getDateTrans());

            //add the transaction to the list for the adapter
            listTransaction.add(trans);

        }

        String jsonList = new Gson().toJson(listTransaction);
        editor.putString("listTransaction",jsonList);


        adapterTransaction.notifyDataSetChanged();



        //return finaList.get(0).getPrix();

    }

    public static UserTransactions newInstance(){
        UserTransactions userTransactions = new UserTransactions();

        return userTransactions;
    }

}
