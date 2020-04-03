package com.loftblog.loftmoney.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.internet.models.AuthResponse;
import com.loftblog.loftmoney.internet.models.GetItemsResponse;
import com.loftblog.loftmoney.internet.models.ItemRemote;
import com.loftblog.loftmoney.internet.WebFactory;
import com.loftblog.loftmoney.screens.second.SecondActivity;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;
import com.loftblog.loftmoney.screens.main.adapter.ChargesAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ChargesAdapter chargesAdapter = new ChargesAdapter();
    public static int NEW_RECORD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);
        recyclerView.setAdapter(chargesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        findViewById(R.id.fabMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivityForResult(intent, NEW_RECORD);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString(AuthResponse.AUTH_TOKEN_KEY, "");

        WebFactory.getInstance().getGetItemsService().request("expense", authToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ItemRemote>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Stub
                    }

                    @Override
                    public void onSuccess(List<ItemRemote> itemRemotes) {
                        List<ChargeModel> chargeModels = new ArrayList<>(itemRemotes.size());
                        for (ItemRemote item: itemRemotes) {
                            ChargeModel chargeModel = new ChargeModel(item);
                            chargeModels.add(chargeModel);
                        }

                        chargesAdapter.setNewData(chargeModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
