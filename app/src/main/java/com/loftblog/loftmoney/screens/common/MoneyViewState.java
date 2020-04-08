package com.loftblog.loftmoney.screens.common;

import com.loftblog.loftmoney.screens.common.adapter.MoneyModel;

import java.util.List;

public interface MoneyViewState {
    void startLoading();
    void setData(List<MoneyModel> items);
    void setError(String error);
    void setNoItems();
}
