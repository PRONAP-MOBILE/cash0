package com.mobil.pronap.cash0.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Transaction {
    private int prix;
    private String description;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Transaction(JSONObject jsonObject) {
        try {
            this.prix = jsonObject.getInt("prix");
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
