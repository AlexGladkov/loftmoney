package com.loftblog.loftmoney.screens.second;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loftblog.loftmoney.LoftApp;
import com.loftblog.loftmoney.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText textName = findViewById(R.id.textSecondName);
        final EditText textValue = findViewById(R.id.textSecondValue);
        Button buttonAdd = findViewById(R.id.btnSecondEnter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                String value = textValue.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(value)) {
                    return;
                }

                sendNewExpense(Integer.valueOf(textValue.getText().toString()),
                        textName.getText().toString());
            }
        });
    }

    // Internal logic
    private void sendNewExpense(Integer price, String name) {
        Disposable disposable = ((LoftApp) getApplication()).postItemRequest().request(price, name, "expense")
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
                    }
                });
    }
}
