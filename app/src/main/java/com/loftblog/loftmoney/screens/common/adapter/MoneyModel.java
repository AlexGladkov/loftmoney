package com.loftblog.loftmoney.screens.main.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.loftblog.loftmoney.screens.web.models.ItemRemote;

import java.io.Serializable;

public class ChargeModel implements Serializable {

    public static String KEY_NAME = ChargeModel.class.getName();

    private String id;
    private String name;
    private String value;
    private int visibility;

    public ChargeModel(String name, String value) {
        this.id = "1";
        this.name = name;
        this.value = value;
        this.visibility = View.VISIBLE;
    }

    public ChargeModel(ItemRemote itemRemote) {
        this.id = itemRemote.getId();
        this.name = itemRemote.getName();
        this.value = itemRemote.getPrice() + " P";
        this.visibility = View.VISIBLE;
    }

    public String getId() {
        return id;
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
