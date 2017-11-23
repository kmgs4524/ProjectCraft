package com.example.york.teamcraft.financefragment.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.accountingitemdialogfragment.AccountingItemFragment;
import com.example.york.teamcraft.financefragment.model.AccountingItem;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/5.
 */

public class AccountingItemAdapter extends RecyclerView.Adapter<AccountingItemViewHolder>{
    private static String TAG = "AccountingItemAdapter";
    private Fragment fragment;
    private ArrayList<AccountingItem> dataList;

    public AccountingItemAdapter(ArrayList<AccountingItem> list, Fragment frag) {
        this.dataList = list;
        this.fragment = frag;
    }

    @Override
    public AccountingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_accounting_item, parent, false);
//        view.setOnClickListener(listener);
        AccountingItemViewHolder holder = new AccountingItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(AccountingItemViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.getTxtName().setText(dataList.get(position).getName());
        holder.getTxtAmount().setText(Integer.toString(dataList.get(position).getAmount()));
        Log.d(TAG, "onBindViewHolder: " + dataList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountingItemFragment addItemFragment = AccountingItemFragment.newInstance(dataList.get(position));
                addItemFragment.show(fragment.getFragmentManager(), "AccountingItemFragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
