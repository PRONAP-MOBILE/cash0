package com.mobil.pronap.cash0.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobil.pronap.cash0.Activities.DetailBuyActivity;
import com.mobil.pronap.cash0.Activities.DrawerActivity;
import com.mobil.pronap.cash0.R;
import com.mobil.pronap.cash0.Utils.LanguageHelper;

/**
 * Created by Emmanuel on 4/12/2018.
 */

public class Settings extends DialogFragment {

    FragmentManager fm;
    Spinner spLang;
    Button btnSetValidation;
    DrawerActivity drawerActivity;
    private String lang;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.settings, null);


        sharedPreferences = getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        fm = getFragmentManager();
        spLang = (Spinner) racine.findViewById(R.id.spLang);
        btnSetValidation = (Button) racine.findViewById(R.id.btnSetValidation);

        lang = sharedPreferences.getString("lang", "HT");



        drawerActivity = (DrawerActivity) getActivity();


        btnSetValidation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                lang = spLang.getSelectedItem().toString();
                switch(lang){
                    case "English":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            LanguageHelper.changeLocal(drawerActivity.getResources(), "en");
                        }else{
                            LanguageHelper.changeLocal(drawerActivity.getResources(), "en");
                        }
                        editor.putString("lang", "en");
                        editor.apply();
                        break;

                    case "Français":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            LanguageHelper.changeLocal(drawerActivity.getResources(), "fr");
                        }else{
                            LanguageHelper.changeLocal(drawerActivity.getResources(), "fr");
                        }
                        editor.putString("lang", "fr");
                        editor.apply();
                        break;
                    case "Kreyol":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            LanguageHelper.changeLocal(drawerActivity.getResources(), "HT");
                        }
                        editor.putString("lang", "HT");
                        editor.apply();
                        break;
                    default:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            LanguageHelper.changeLocal(drawerActivity.getResources(), "en");
                        }
                        editor.putString("lang", "en");
                        editor.apply();
                        break;

                }
                dismiss();
                Toast.makeText(drawerActivity, R.string.restart, Toast.LENGTH_SHORT).show();
                drawerActivity.restartApp();
            }
        });




        return  racine;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

}
