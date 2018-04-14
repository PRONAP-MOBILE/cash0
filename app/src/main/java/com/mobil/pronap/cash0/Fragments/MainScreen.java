package com.mobil.pronap.cash0.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.mobil.pronap.cash0.Activities.DetailSellActivity;
import com.mobil.pronap.cash0.Activities.LoginActivity;
import com.mobil.pronap.cash0.Activities.MainActivity;
import com.mobil.pronap.cash0.Activities.ProfilActivity;
import com.mobil.pronap.cash0.R;

import static com.mobil.pronap.cash0.R.string.quit_message;

/**
 * Created by jetro on 4/12/18.
 */

public class MainScreen extends Fragment {

    //ImageButton ibProfil;

    Button btnBuy;
    Button btnSell;
    Intent i;
    Toolbar customToolbar;
    SharedPreferences sharedPreferences;
    Boolean exitApp = false;
    View racine;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        racine = inflater.inflate(R.layout.mainscreen, container, false);
        sharedPreferences = getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        final Activity activity = getActivity();

        init_views();





        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check if user already login
                //Make the correct redirection
                if(sharedPreferences.getString("infoUser", null)==null || sharedPreferences.getString("infoUser", null).equals("")){
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                }else{
                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan en cours");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
                }

            }
        });



        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Check if user already login
                //Make the correct redirection
                if(sharedPreferences.getString("infoUser", null)==null || sharedPreferences.getString("infoUser", null).equals("")){
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                }else{
                    getActivity().startActivity(new Intent(getActivity(), DetailSellActivity.class));
                }
            }
        });

        return racine;
    }


    public void init_views(){

        btnBuy = (Button) racine.findViewById(R.id.btnBuy);
        btnSell = (Button) racine.findViewById(R.id.btnSell);
        customToolbar = (Toolbar) racine.findViewById(R.id.customToolbar);


        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        //BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        //myAnim.setInterpolator(interpolator);
        btnBuy.startAnimation(myAnim);
        btnSell.startAnimation(myAnim);

    }


}
