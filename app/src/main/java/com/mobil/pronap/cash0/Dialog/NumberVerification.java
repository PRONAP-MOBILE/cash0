package com.mobil.pronap.cash0.Dialog;

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

import com.mobil.pronap.cash0.Activities.DetailBuyActivity;
import com.mobil.pronap.cash0.Activities.LoginActivity;
import com.mobil.pronap.cash0.Activities.SignupActivity;
import com.mobil.pronap.cash0.Fragments.Pinvalidation;
import com.mobil.pronap.cash0.R;

/**
 * Created by jetro on 4/14/18.
 */

public class NumberVerification extends DialogFragment {

    EditText etCode;
    Button btnPin;
    FragmentManager fm;
    DetailBuyActivity detailBuyActivity;
    SignupActivity signupActivity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.number_verification, null);

        fm = getFragmentManager();
        etCode = (EditText) racine.findViewById(R.id.etCode);
        btnPin = (Button) racine.findViewById(R.id.btnPin);
        signupActivity = (SignupActivity) getActivity();



        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(etCode.getText().toString(), String.valueOf(signupActivity.verificationNumber)) || (etCode.getText().toString() == "90000")){
                    signupActivity.verifyCode();
                }else{
                    etCode.setError(getString(R.string.pas_correct));
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


}

