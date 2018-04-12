package com.mobil.pronap.cash0.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 4/10/18.
 */

public class User {

    private String phone;
    private String password;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){

    }

    public User(JSONObject jsonObject) {
        try {
            this.phone = jsonObject.getString("phone");
            this.password = jsonObject.getString("password");

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
