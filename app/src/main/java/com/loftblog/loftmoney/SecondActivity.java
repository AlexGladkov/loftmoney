package com.loftblog.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loftblog.loftmoney.screens.main.MainActivity;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText textName = findViewById(R.id.textSecondName);
        final EditText textValue = findViewById(R.id.textSecondValue);
        Button btnAdd = findViewById(R.id.btnSecondAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(textName.getText()) && TextUtils.isEmpty(textValue.getText())) {
                    return;
                }

                ChargeModel chargeModel = new ChargeModel(textValue.getText().toString() + " P",
                        textName.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra(ChargeModel.KEY_NAME, chargeModel);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
