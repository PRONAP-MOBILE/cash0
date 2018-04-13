package com.mobil.pronap.cash0.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 4/10/18.
 */

public class Card {

    private int cardNumber;
    private int noCompt;
    private int routingNumberBank;
    private String userdId;

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getNoCompt() {
        return noCompt;
    }

    public void setNoCompt(int noCompt) {
        this.noCompt = noCompt;
    }

    public int getRoutingNumberBank() {
        return routingNumberBank;
    }

    public void setRoutingNumberBank(int routingNumberBank) {
        this.routingNumberBank = routingNumberBank;
    }

    public Card(){

    }


    public String getUserdId() {
        return userdId;
    }

    public void setUserdId(String userdId) {
        this.userdId = userdId;
    }


    public Card(JSONObject jsonObject) {
        try {
            this.cardNumber = jsonObject.getInt("cardNumbder");
            this.noCompt = jsonObject.getInt("noCompt");
            this.routingNumberBank = jsonObject.getInt("routingNumberBank");
            this.userdId = jsonObject.getString("userID");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<Card> fromJson(JSONArray jsonArray){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i=0; i < jsonArray.length(); i++){
            try {
                cards.add(new Card(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return cards;
    }

}
