package com.example.york.teamcraft.finance.view;

import com.example.york.teamcraft.finance.model.AccountingItem;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/5.
 */

public interface FinanceView {
    public abstract void initRecyclerView(ArrayList<AccountingItem> list);
}
