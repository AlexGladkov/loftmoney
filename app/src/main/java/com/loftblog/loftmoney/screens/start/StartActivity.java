package com.loftblog.loftmoney.screens.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.internet.AuthService;
import com.loftblog.loftmoney.internet.WebFactory;
import com.loftblog.loftmoney.internet.models.AuthResponse;
import com.loftblog.loftmoney.screens.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StartActivity extends AppCompatActivity {

    private List<Disposable> disposables = new ArrayList<>();
    private Button authButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        authButton = findViewById(R.id.btnStartSignIn);
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignIn();
            }
        });
    }

    @Override
    protected void onDestroy() {
        for (Disposable disposable: disposables) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    private void performSignIn() {
        authButton.setVisibility(View.INVISIBLE);
        Disposable request = WebFactory.getInstance().getAuthService().request("Alex Gladkov")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AuthResponse>() {
                    @Override
                    public void accept(AuthResponse authResponse) throws Exception {
                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString(AuthResponse.AUTH_TOKEN_KEY, authResponse.getAuthToken()).apply();

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        authButton.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        disposables.add(request);
    }
}
