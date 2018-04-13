package com.mobil.pronap.cash0.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jetro on 4/10/18.
 */

public class Card {

    private String cardNumber;
    private String noCompt;
    private String routingNumberBank;
    private String userdId;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNoCompt() {
        return noCompt;
    }

    public void setNoCompt(String noCompt) {
        this.noCompt = noCompt;
    }

    public String getRoutingNumberBank() {
        return routingNumberBank;
    }

    public void setRoutingNumberBank(String routingNumberBank) {
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
            this.cardNumber = jsonObject.getString("cardNumbder");
            this.noCompt = jsonObject.getString("noCompt");
            this.routingNumberBank = jsonObject.getString("routingNumberBank");
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
