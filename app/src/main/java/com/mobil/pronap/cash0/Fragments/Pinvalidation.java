package com.mobil.pronap.cash0.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mobil.pronap.cash0.Activities.DetailBuyActivity;
import com.mobil.pronap.cash0.R;

/**
 * Created by Emmanuel on 4/11/2018.
 */

public class Pinvalidation extends DialogFragment {

    EditText etPin;
    Button btnPin;
    FragmentManager fm;
    DetailBuyActivity detailBuyActivity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.pin_validation, null);

        fm = getFragmentManager();
        etPin = (EditText) racine.findViewById(R.id.etPin);
        btnPin = (Button) racine.findViewById(R.id.btnPin);
        detailBuyActivity = (DetailBuyActivity) getActivity();



        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etPin.getText().toString())){
                    if(etPin.getText().toString().length() == 4){
                        if(Integer.parseInt(etPin.getText().toString()) == 1234){
                            detailBuyActivity.onReceivePin(etPin.getText().toString());
                            dismiss();
                        }else{
                            etPin.setError("PIN incorrect");
                        }
                    }else{
                        etPin.setError("Le PIN doit contenir 4 chiffres");
                    }
                }else{
                    etPin.setError("Champs vide");
                }
            }
        });


        return  racine;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }



    //Definition de l'interface permettant d'envoyer les informations du contact pour les enregistrer
    public interface PinListener{
        void onFinishEnterPin(int userPin);
    }
    //methode utilisant l'interface pour faire passer les donnees
    public void SendPin(){
        PinListener listener = (PinListener) getTargetFragment();
        listener.onFinishEnterPin(Integer.parseInt(etPin.getText().toString()));
    }

}
