package com.loftblog.loftmoney.screens.common.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MoneyDiffUtils extends DiffUtil.Callback {

    private final List<MoneyModel> oldList;
    private final List<MoneyModel> newList;

    public MoneyDiffUtils(List<MoneyModel> oldList, List<MoneyModel> newList) {
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
        return oldList.get(oldItemPosition).getName().equals(newList.get(newItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        boolean isTitlesSame = oldList.get(oldItemPosition).getName().equals(newList.get(newItemPosition).getName());
        boolean isValuesSame = oldList.get(oldItemPosition).getValue().equals(newList.get(newItemPosition).getValue());
        return isTitlesSame && isValuesSame;
    }
}
