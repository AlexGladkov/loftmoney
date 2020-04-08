package com.loftblog.loftmoney.screens.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import com.loftblog.loftmoney.LoftApp;
import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;
import com.loftblog.loftmoney.screens.web.GetItemsRequest;
import com.loftblog.loftmoney.screens.web.models.AuthResponse;
import com.loftblog.loftmoney.screens.web.models.ItemRemote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private List<Disposable> disposables = new ArrayList();
    private MainViewState mainViewState = null;

    public void setMainViewState(MainViewState mainViewState) {
        this.mainViewState = mainViewState;
    }

    // Internal logic
    void onDestroy() {
        for (Disposable disposable: disposables) {
            disposable.dispose();
        }
    }

    void loadItems(String authToken, GetItemsRequest getItemsRequest) {
        mainViewState.startLoading();
        Disposable response =  getItemsRequest.request("expense", authToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ItemRemote>>() {
                    @Override
                    public void accept(List<ItemRemote> itemRemotes) throws Exception {
                        final List<ChargeModel> chargeModels = new ArrayList<>();
                        for (ItemRemote itemRemote: itemRemotes) {
                            chargeModels.add(new ChargeModel(itemRemote));
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (chargeModels.isEmpty()) {
                                    mainViewState.setNoItems();
                                }  else {
                                    mainViewState.setData(chargeModels);
                                }
                            }
                        }, 4000);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mainViewState.setError(throwable.getLocalizedMessage());
                    }
                });

        disposables.add(response);
    }
}
