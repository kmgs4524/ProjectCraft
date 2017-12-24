package com.example.york.teamcraft.financefragment.view;

import com.example.york.teamcraft.financefragment.model.AccountingItem;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/5.
 */

public interface FinanceView {
    public abstract void initProgressBar(int budget, int totalCost);
    public abstract void initTxtBuget(int budget);
    public abstract void initRecyclerView(ArrayList<AccountingItem> list);
    public abstract void initTxtTotalCost(int cost);
    public abstract void initFloatingButton();
    public abstract void showEmptyState();
    public abstract void hideEmptyState();
    public abstract void showUsualState();
    public abstract void hideUsualState();
}
