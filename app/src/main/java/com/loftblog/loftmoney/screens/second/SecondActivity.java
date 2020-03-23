package com.loftblog.loftmoney.screens.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;

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

                String valueString = value + " P";
                ChargeModel chargeModel = new ChargeModel(name, valueString);

                Intent intent = new Intent();
                intent.putExtra(ChargeModel.KEY_NAME, chargeModel);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
