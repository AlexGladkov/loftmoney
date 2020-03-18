package com.loftblog.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        try {
            String title = getIntent().getExtras().getString("Title");
            Log.e("TAG", "Title is " + title);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
