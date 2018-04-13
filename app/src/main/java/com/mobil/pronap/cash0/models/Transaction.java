package com.mobil.pronap.cash0.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 4/10/18.
 */

public class Transaction {

    private String prix;
    private String description;
    String dateTrans;

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans  (String dateTrans) {
        this.dateTrans = dateTrans;
    }

    public Transaction(){

    }


    public Transaction(JSONObject jsonObject) {
        try {
            this.prix = jsonObject.getString("prix");
            this.description = jsonObject.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<Transaction> fromJson(JSONArray jsonArray){
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        for(int i = 0 ; i < jsonArray.length(); i++ ){
            try {
                transactions.add(new Transaction(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();
                e.printStackTrace();
            }
        }
        return transactions;
    }


}
