package com.example.york.teamcraft.financefragment.model;

/**
 * Created by York on 2017/11/5.
 */

public class AccountingItem {
    private String name;
    private int amount;
    private String date;
    private String payer;
    private String type;

    public AccountingItem(String name, int amount, String date, String payer) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.payer = payer;
    }

    public AccountingItem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
