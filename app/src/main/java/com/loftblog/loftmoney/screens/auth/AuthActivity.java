package com.loftblog.loftmoney.screens.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.loftblog.loftmoney.LoftApp;
import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.main.MainActivity;
import com.loftblog.loftmoney.screens.web.models.AuthResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AuthActivity extends AppCompatActivity {

    private List<Disposable> disposeBag = new ArrayList<>();
    private Button btnAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnAuth = findViewById(R.id.btnAuthSignIn);
        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }

    @Override
    protected void onDestroy() {
        for (Disposable disposable: disposeBag) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    // Internal logic
    private void startSignIn() {
        btnAuth.setVisibility(View.INVISIBLE);

        Disposable request = ((LoftApp) getApplication()).getAuthRequest().request("AlexGladkov1")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AuthResponse>() {
                    @Override
                    public void accept(AuthResponse authResponse) throws Exception {
                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name),
                                Context.MODE_PRIVATE);
                        sharedPreferences.edit()
                                .putString(AuthResponse.AUTH_TOKEN_KEY, authResponse.getAuthToken())
                                .apply();

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        btnAuth.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Error - " + throwable.getLocalizedMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        disposeBag.add(request);
    }
}
