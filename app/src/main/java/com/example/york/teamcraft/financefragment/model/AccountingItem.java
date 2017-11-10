package com.example.york.teamcraft.financefragment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by York on 2017/11/5.
 */

public class AccountingItem implements Parcelable{
    private String name;
    private int amount;
    private String date;
    private String payer;
    private String type;

    public AccountingItem(String name, int amount, String date, String payer, String type) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.payer = payer;
        this.type = type;
    }

    public AccountingItem() {}

    protected AccountingItem(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        date = in.readString();
        payer = in.readString();
        type = in.readString();
    }

    public static final Creator<AccountingItem> CREATOR = new Creator<AccountingItem>() {
        @Override
        public AccountingItem createFromParcel(Parcel in) {
            return new AccountingItem(in);
        }

        @Override
        public AccountingItem[] newArray(int size) {
            return new AccountingItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amount);
        dest.writeString(date);
        dest.writeString(payer);
        dest.writeString(type);
    }
}
