package com.loftblog.loftmoney.screens.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.loftblog.loftmoney.LoftApp;
import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.balance.BalanceFragment;
import com.loftblog.loftmoney.screens.expenses.ExpensesFragment;
import com.loftblog.loftmoney.screens.incomes.IncomesFragment;
import com.loftblog.loftmoney.screens.main.adapter.MainViewPager;
import com.loftblog.loftmoney.screens.web.GetItemsRequest;
import com.loftblog.loftmoney.screens.web.models.AuthResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ExpensesFragment());
        fragments.add(new IncomesFragment());
        fragments.add(new BalanceFragment());

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.title_expenses));
        titles.add(getString(R.string.title_incomes));
        titles.add(getString(R.string.title_balance));

        MainViewPager mainViewPager = new MainViewPager(getSupportFragmentManager(), fragments,
                titles);

        ViewPager viewPager = findViewById(R.id.vpMain);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mainViewPager);
        TabLayout tabLayout = findViewById(R.id.tlMain);

        tabLayout.setupWithViewPager(viewPager);
    }
}
