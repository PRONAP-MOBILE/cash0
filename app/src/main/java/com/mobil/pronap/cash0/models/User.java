package com.mobil.pronap.cash0.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 4/10/18.
 */

public class User {

    private String nif;
    private String phone;
    private String email;
    private String userName;
    private int cardNumber;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User(JSONObject jsonObject) {
        try {
            this.nif = jsonObject.getString("nif");
            this.phone = jsonObject.getString("phone");
            this.email = jsonObject.getString("email");
            this.userName = jsonObject.getString("userName");
            this.cardNumber = jsonObject.getInt("cardNumber");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<User> fromJson(JSONArray jsonArray){
        ArrayList<User> users = new ArrayList<User>();
        for (int i=0; i< jsonArray.length();i++){

            try {
                users.add(new User(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  users;
    }
}
