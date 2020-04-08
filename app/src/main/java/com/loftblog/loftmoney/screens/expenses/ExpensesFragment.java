package com.loftblog.loftmoney.screens.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.loftblog.loftmoney.LoftApp;
import com.loftblog.loftmoney.R;
import com.loftblog.loftmoney.screens.common.MoneyPresenter;
import com.loftblog.loftmoney.screens.common.MoneyViewState;
import com.loftblog.loftmoney.screens.common.adapter.MoneyModel;
import com.loftblog.loftmoney.screens.common.adapter.MoneyAdapter;
import com.loftblog.loftmoney.screens.main.adapter.TitleProvider;
import com.loftblog.loftmoney.screens.web.GetItemsRequest;
import com.loftblog.loftmoney.screens.web.models.AuthResponse;

import java.util.List;

public class ExpensesFragment extends Fragment implements MoneyViewState, TitleProvider {

    private RecyclerView recyclerView;
    private CircularProgressView cpvLoader;
    private View notFound;
    private MoneyAdapter moneyAdapter = new MoneyAdapter();
    private MoneyPresenter moneyPresenter = new MoneyPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerExpenses);
        cpvLoader = view.findViewById(R.id.cpvExpenses);
        notFound = view.findViewById(R.id.llExpensesNoItems);

        moneyPresenter.setMainViewState(this);
    }

    // Presenter implementation
    @Override
    public void onDestroy() {
        moneyPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() == null || getContext() == null || getActivity().getApplication() == null) {
            return;
        }

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString(AuthResponse.AUTH_TOKEN_KEY, "");

        GetItemsRequest getItemsRequest = ((LoftApp) getActivity().getApplication()).getItemsRequest();

        moneyPresenter.loadItems(authToken, getItemsRequest, "expenses");
    }

    // TitleProvider implementation
    @Override
    public String getTitle() {
        return getString(R.string.title_expenses);
    }

    // ViewState implementation
    @Override
    public void startLoading() {
        cpvLoader.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        notFound.setVisibility(View.GONE);
    }

    @Override
    public void setData(List<MoneyModel> items) {
        cpvLoader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        notFound.setVisibility(View.GONE);

        moneyAdapter.setNewData(items);
    }

    @Override
    public void setError(String error) {
        cpvLoader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        notFound.setVisibility(View.VISIBLE);

        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setNoItems() {
        cpvLoader.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        notFound.setVisibility(View.VISIBLE);
    }
}
