package com.example.york.teamcraft.financefragment.view;

import com.example.york.teamcraft.financefragment.model.AccountingItem;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/5.
 */

public interface FinanceView {
    public abstract void initRecyclerView(ArrayList<AccountingItem> list);
    public abstract void initFab();
}