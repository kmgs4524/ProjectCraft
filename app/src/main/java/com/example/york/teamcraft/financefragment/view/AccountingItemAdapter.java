package com.example.york.teamcraft.financefragment.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.financefragment.model.AccountingItem;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/5.
 */

public class AccountingItemAdapter extends RecyclerView.Adapter<AccountingItemViewHolder>{
    private ArrayList<AccountingItem> dataList;

    public AccountingItemAdapter(ArrayList<AccountingItem> list) {
        this.dataList = list;
    }

    @Override
    public AccountingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_accounting_item, parent, false);
        AccountingItemViewHolder holder = new AccountingItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(AccountingItemViewHolder holder, int position) {
        holder.getTxtName().setText(dataList.get(position).getName());
        holder.getTxtAmount().setText(Integer.toString(dataList.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
