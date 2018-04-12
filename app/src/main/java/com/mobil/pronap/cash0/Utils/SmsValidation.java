package com.mobil.pronap.cash0.Utils;

import android.widget.Toast;

/**
 * Created by Emmanuel on 4/11/2018.
 */

public  class SmsValidation {

    private SmsValidation(){}

    public static boolean sendBuyConfirmation(String phone, String p_name, String p_detail, String p_price){
        String sms = "Vous venez d'achetez un produit sur la plateforme Cash 0.\n Nom du produit: "+ p_name +" \n Note: "+ p_detail + " \n Montant: " + p_price +" \n" + "Merci d'utiliser la plateforme Cash 0 \n";
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendSellConfirmation(String phone,String p_name, String p_detail, String p_price){
        String sms = "Vous venez de vendre un produit sur la plateforme Cash 0.\n Nom du produit: "+ p_name +" \n Note: "+ p_detail + " \n Montant: " + p_price +" \n" + "Le montant sera directement debiter sur votre compte. Merci d'utiliser la plateforme Cash 0 \n";
        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, sms, null, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
