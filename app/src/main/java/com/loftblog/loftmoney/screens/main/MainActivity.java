package com.loftblog.loftmoney.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.loftblog.loftmoney.LoftApp;
import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;
import com.loftblog.loftmoney.screens.main.adapter.ChargesAdapter;
import com.loftblog.loftmoney.screens.second.SecondActivity;
import com.loftblog.loftmoney.screens.web.GetItemsRequest;
import com.loftblog.loftmoney.screens.web.models.AuthResponse;
import com.loftblog.loftmoney.screens.web.models.GetItemsResponseModel;
import com.loftblog.loftmoney.screens.web.models.ItemRemote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainViewState {

    private ChargesAdapter chargesAdapter = new ChargesAdapter();
    private MainPresenter mainPresenter = new MainPresenter();
    private RecyclerView recyclerView;
    private CircularProgressView cpvLoader;
    private View notFound;
    static int ADD_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpvLoader = findViewById(R.id.cpvMain);
        notFound = findViewById(R.id.llMainNoItems);
        recyclerView = findViewById(R.id.recyclerMain);
        recyclerView.setAdapter(chargesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                false));

        findViewById(R.id.fabMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivityForResult(secondIntent, ADD_ITEM_REQUEST);
            }
        });

        mainPresenter.setMainViewState(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString(AuthResponse.AUTH_TOKEN_KEY, "");

        GetItemsRequest getItemsRequest = ((LoftApp) getApplication()).getItemsRequest();

        mainPresenter.loadItems(authToken, getItemsRequest);
    }

    @Override
    protected void onStop() {
        mainPresenter.onDestroy();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // MainViewState implementation
    @Override
    public void startLoading() {
        cpvLoader.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        notFound.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<ChargeModel> items) {
        cpvLoader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        notFound.setVisibility(View.GONE);

        chargesAdapter.setNewData(items);
    }

    @Override
    public void setError(String error) {
        cpvLoader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        notFound.setVisibility(View.VISIBLE);

        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setNoItems() {
        cpvLoader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        notFound.setVisibility(View.VISIBLE);
    }
}
