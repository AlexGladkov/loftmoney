package com.loftblog.loftmoney.screens.main.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import com.loftblog.loftmoney.internet.ItemRemote;

import java.io.Serializable;

public class ChargeModel implements Serializable {

    public static String KEY_NAME = "charge_model";

    private String value;
    private String title;

    public ChargeModel(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public ChargeModel(ItemRemote itemRemote) {
        this.title = itemRemote.getName();
        this.value = String.valueOf(itemRemote.getPrice()) + " Р";
    }

    String getValue() {
        return value;
    }
    String getTitle() {
        return title;
    }
}
