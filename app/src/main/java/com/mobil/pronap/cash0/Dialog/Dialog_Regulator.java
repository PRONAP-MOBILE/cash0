package com.mobil.pronap.cash0.Dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mobil.pronap.cash0.R;

/**
 * Created by jetro on 4/13/18.
 */

public class Dialog_Regulator extends DialogFragment {

    //variable for storing limit spend
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    private EditText prixLimit;
    private Button save;


    public Dialog_Regulator() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static Dialog_Regulator newInstance(String title) {
        Dialog_Regulator frag = new Dialog_Regulator();
        Bundle args = new Bundle();
        args.putString("title", title);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_regulator, container);

        sharedPreferences = getActivity().getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        prixLimit = v.findViewById(R.id.edtLimitSpend);
        save = v.findViewById(R.id.btLimitSpend);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String prix = prixLimit.getText().toString();
                editor.putString("prixLimit", prix);
                editor.apply();
                dismiss();


            }
        });

        return  v;


    }

}
