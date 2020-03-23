package com.loftblog.loftmoney.screens.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.main.adapter.ChargeDiffUtils;
import com.loftblog.loftmoney.screens.second.SecondActivity;
import com.loftblog.loftmoney.screens.main.adapter.ChargeModel;
import com.loftblog.loftmoney.screens.main.adapter.ChargesAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

        List<ChargeModel> testList = new ArrayList<>();
        testList.add(new ChargeModel("500 P", "House"));
        testList.add(new ChargeModel("200 P", "Children"));
        testList.add(new ChargeModel("300 P", "Cat"));
        testList.add(new ChargeModel("150 P", "Bills"));

        chargesAdapter.setNewData(testList);

        findViewById(R.id.fabMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyDiffUtils();
//                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//                startActivityForResult(intent, NEW_RECORD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_RECORD && resultCode == RESULT_OK && data != null) {
            ChargeModel chargeModel = (ChargeModel) data.getSerializableExtra(ChargeModel.KEY_NAME);
            chargesAdapter.addNewRecord(chargeModel);
        }
    }

    private void applyDiffUtils() {
        List<ChargeModel> testList = new ArrayList<>();
        testList.add(new ChargeModel("300 P", "House"));
        testList.add(new ChargeModel("250 P", "Bills"));
        testList.add(new ChargeModel("500 P", "Cat"));
        testList.add(new ChargeModel("250 P", "Children"));

        ChargeDiffUtils chargeDiffUtils = new ChargeDiffUtils(chargesAdapter.getData(), testList);
        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(chargeDiffUtils);

        chargesAdapter.setData(testList);
        productDiffResult.dispatchUpdatesTo(chargesAdapter);
    }
}
