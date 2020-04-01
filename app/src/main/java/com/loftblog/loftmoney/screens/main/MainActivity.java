package com.loftblog.loftmoney.screens.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.internet.GetItemsResponse;
import com.loftblog.loftmoney.internet.ItemRemote;
import com.loftblog.loftmoney.internet.WebFactory;
import com.loftblog.loftmoney.screens.second.SecondActivity;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;
import com.loftblog.loftmoney.screens.main.adapter.ChargesAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_RECORD && resultCode == RESULT_OK && data != null) {
//            ChargeModel chargeModel = (ChargeModel) data.getSerializableExtra(ChargeModel.KEY_NAME);
//            chargesAdapter.addNewRecord(chargeModel);
//        }
//    }

    private void loadItems() {
        WebFactory.getInstance().getGetItemsService().request("expense")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetItemsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Stub
                    }

                    @Override
                    public void onSuccess(GetItemsResponse getItemsResponse) {
                        List<ChargeModel> chargeModels = new ArrayList<>(getItemsResponse.getData().size());
                        for (ItemRemote item: getItemsResponse.getData()) {
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
