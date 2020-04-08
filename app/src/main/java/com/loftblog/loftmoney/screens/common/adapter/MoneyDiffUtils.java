package com.loftblog.loftmoney.screens.main.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class ChargeDiffUtils extends DiffUtil.Callback {

    private final List<ChargeModel> oldList;
    private final List<ChargeModel> newList;

    public ChargeDiffUtils(List<ChargeModel> oldList, List<ChargeModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTitle().equals(newList.get(newItemPosition).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        boolean isTitlesSame = oldList.get(oldItemPosition).getTitle().equals(newList.get(newItemPosition).getTitle());
        boolean isValuesSame = oldList.get(oldItemPosition).getValue().equals(newList.get(newItemPosition).getValue());
        return isTitlesSame && isValuesSame;
    }
}
