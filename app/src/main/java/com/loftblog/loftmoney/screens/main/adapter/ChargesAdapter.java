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

    public void setNewData(List<ChargeModel> chargeModels) {
        mDataList.clear();
        mDataList.addAll(chargeModels);
        notifyDataSetChanged();
    }

    public void addNewRecord(ChargeModel chargeModel) {
        mDataList.add(chargeModel);
        notifyItemInserted(mDataList.size() - 1);
    }

    @NonNull
    @Override
    public ChargesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChargesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_charge, parent, false));
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
        private TextView txtTitle = itemView.findViewById(R.id.txtChargeTitle);
        private TextView txtValue = itemView.findViewById(R.id.txtChargeValue);

        ChargesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(ChargeModel model) {
            txtTitle.setText(model.getTitle());
            txtValue.setText(model.getValue());
        }
    }
}
