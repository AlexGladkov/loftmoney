package com.loftblog.loftmoney.screens.incomes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.main.adapter.TitleProvider;

public class IncomesFragment extends Fragment implements TitleProvider {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_incomes, container, false);
    }

    // TitleProvider implementation
    @Override
    public String getTitle() {
        return getString(R.string.title_expenses);
    }
}
