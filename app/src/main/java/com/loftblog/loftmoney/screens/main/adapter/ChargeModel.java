package com.loftblog.loftmoney.screens.main.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;

public class ChargeModel implements Serializable {

    public static String KEY_NAME = ChargeModel.class.getName();

    private String name;
    private String value;
    private int visibility;

    public ChargeModel(String name, String value) {
        this.name = name;
        this.value = value;
        this.visibility = View.VISIBLE;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
