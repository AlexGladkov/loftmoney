package com.loftblog.loftmoney.screens.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.internet.WebFactory;
import com.loftblog.loftmoney.screens.main.MainActivity;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    private List<Disposable> disposables = new ArrayList<>();
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText textName = findViewById(R.id.textSecondName);
        final EditText textValue = findViewById(R.id.textSecondValue);
        btnAdd = findViewById(R.id.btnSecondAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(textName.getText()) && TextUtils.isEmpty(textValue.getText())) {
                    return;
                }

                sendNewExpense(Integer.valueOf(textValue.getText().toString()),
                        textName.getText().toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }

        super.onDestroy();
    }

    private void sendNewExpense(Integer price, String name) {
        btnAdd.setEnabled(false);
        Disposable request = WebFactory.getInstance().addItemService().request("expense", name, price)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(getApplicationContext(), getString(R.string.message_success), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getApplicationContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        btnAdd.setEnabled(true);
                    }
                });

        disposables.add(request);
    }
}
