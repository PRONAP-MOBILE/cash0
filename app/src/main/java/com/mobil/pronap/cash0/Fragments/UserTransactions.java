package com.mobil.pronap.cash0.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobil.pronap.cash0.R;

/**
 * Created by jetro on 4/9/18.
 */

public class UserTransactions extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.user_transaction, container, false);

        


        return v;

    }

    public static UserTransactions newInstance(){
        UserTransactions userTransactions = new UserTransactions();

        return userTransactions;
    }

}
