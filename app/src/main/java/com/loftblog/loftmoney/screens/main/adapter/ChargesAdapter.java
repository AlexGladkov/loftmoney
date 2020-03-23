package com.loftblog.loftmoney.screens.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loftblog.loftmoney.R;

import java.util.ArrayList;
import java.util.List;

public class ChargesAdapter extends RecyclerView.Adapter<ChargesAdapter.ChargesViewHolder> {

    private List<ChargeModel> mDataList = new ArrayList<>();

    public void setNewData(List<ChargeModel> newData) {
        mDataList.clear();
        mDataList.addAll(newData);
        mDataList.get(mDataList.size() - 1).setVisibility(View.GONE);
        notifyDataSetChanged();
    }

    public void addDataToTop(ChargeModel model) {
        mDataList.add(0, model);
        mDataList.get(mDataList.size() - 1).setVisibility(View.GONE);
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public ChargesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ChargesViewHolder(layoutInflater.inflate(R.layout.cell_charge, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChargesViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ChargesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName = itemView.findViewById(R.id.txtChargeName);
        private TextView txtValue = itemView.findViewById(R.id.txtChargeValue);
        private View divider = itemView.findViewById(R.id.viewChargeDivider);

        ChargesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(ChargeModel chargeModel) {
            txtName.setText(chargeModel.getName());
            txtValue.setText(chargeModel.getValue());
            divider.setVisibility(chargeModel.getVisibility());
        }
    }
}
