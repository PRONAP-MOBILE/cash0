
/*
package com.mobil.pronap.cash0.Dialog;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobil.pronap.cash0.Activities.DetailBuyActivity;
import com.mobil.pronap.cash0.Fragments.Pinvalidation;
import com.mobil.pronap.cash0.R;
import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;






public class Fingerprint extends DialogFragment implements FingerPrintAuthCallback {

    TextView tvFpInfo;
    TextView tvCancelFP;
    TextView tvUsePin;
    ImageView ivFingerPrint;
    FragmentManager fm;
    Pinvalidation pinvalidation;
    DetailBuyActivity detailBuyActivity;
    private FingerPrintAuthHelper mFingerPrintAuthHelper;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View racine = inflater.inflate(R.layout.fingerprint, null);

        fm = getFragmentManager();
        tvCancelFP = racine.findViewById(R.id.tvCancelFP);
        tvFpInfo = racine.findViewById(R.id.tvFpInfo);
        tvUsePin = racine.findViewById(R.id.tvUsePin);
        pinvalidation = new Pinvalidation();
        ivFingerPrint = racine.findViewById(R.id.ivFingerPrint);

        detailBuyActivity = (DetailBuyActivity) getActivity();

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(detailBuyActivity, this);



        return  racine;
    }

    @Override
    public void onResume() {
        super.onResume();


        tvFpInfo.setText(R.string.fingerprint_scan);

        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mFingerPrintAuthHelper.stopAuth();
        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }


    @Override
    public void onNoFingerPrintHardwareFound() {
        tvFpInfo.setText(R.string.fingerprint_permission);
        pinvalidation.show(fm, "PIN");
        dismiss();

    }

    @Override
    public void onNoFingerPrintRegistered() {
        tvFpInfo.setText(R.string.fingerprint_register);
        pinvalidation.show(fm, "PIN");
    }

    @Override
    public void onBelowMarshmallow() {
        //tvFpInfo.setText(R.string.fingerprint_support);
        pinvalidation.show(fm, "PIN");
        dismiss();
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {

        Toast.makeText(detailBuyActivity, R.string.fingerprint_succes, Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(MainActivity.this, AuthSuccessScreen.class));
        detailBuyActivity.onReceivePin(1234);
        dismiss();
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {

        switch (errorCode) {
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                tvFpInfo.setText(R.string.fingerprint_error1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pinvalidation.show(fm, "PIN");
                        dismiss();
                    }
                }, 2000);


                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                tvFpInfo.setText(R.string.fingerprint_error2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pinvalidation.show(fm, "PIN");
                        dismiss();
                    }
                }, 2000);
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                tvFpInfo.setText(errorMessage);
                //Go to pin
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pinvalidation.show(fm, "PIN");
                        dismiss();
                    }
                }, 2000);
                break;
        }

    }
}

*/

