package com.mobil.pronap.cash0.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.models.Card;
import com.mobil.pronap.cash0.models.User;

/**
 * Created by jetro on 4/9/18.
 */

public class UserInfo extends Fragment {


    TextView tvLName;
    TextView tvtel;
    TextView tvbankPicked;
    TextView tvcardNumber;
    TextView tvaccountNumber;
    ImageView ibSettings;
    FragmentManager fm;
    Settings settings;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    private String userInfo;
    private String cardInfo;
    private User user;
    private Card card;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.user_info, container, false);
        tvLName = v.findViewById(R.id.tvLName);
        tvtel = v.findViewById(R.id.tvTel);
        tvbankPicked = v.findViewById(R.id.tvBankPicked);
        tvcardNumber = v.findViewById(R.id.tvCardNumber);
        tvaccountNumber = v.findViewById(R.id.tvAccountNumber);
        ibSettings = v.findViewById(R.id.ibSettings);

        settings = new Settings();
        fm = getFragmentManager();


        sharedPreferences = getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        userInfo = sharedPreferences.getString("userRegister", null);
        cardInfo = sharedPreferences.getString("cardRegister", null);
        user = gson.fromJson(userInfo, User.class);
        card = gson.fromJson(cardInfo, Card.class);


        tvLName.setText(user.getName());
        tvtel.setText(user.getPhone());
        tvbankPicked.setText(card.getType());
        tvaccountNumber.setText("No Compte: XXXXXXXXXXX" + card.getCardNumber().substring(12));
        tvcardNumber.setText("No Carte: XXXXXXXXXXX" + card.getCardNumber().substring(12));

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.show(fm, "SETTINGS");
            }
        });


        return v;

    }


    public static UserInfo newInstance(){
        UserInfo userInfo = new UserInfo();

        return userInfo;
    }

}

