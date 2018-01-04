package com.example.york.teamcraft.financefragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.financefragment.model.ReadTeamFinance;
import com.example.york.teamcraft.financefragment.view.FinanceView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/5.
 */

public class SetAccountingItem {
    private FinanceView financeView;

    public SetAccountingItem(FinanceView view) {
        this.financeView = view;
    }

    public void setRecyclerViewData() {
        ReadUser readUser = new ReadUser();
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                ReadTeamFinance readTeamFinance = new ReadTeamFinance();
                readTeamFinance.getAccountingItem(user.getTeamId(), new CallBack<ArrayList<AccountingItem>>() {
                    @Override
                    public void update(ArrayList<AccountingItem> accountingItems) {
                        // 設定品項資料
                        financeView.initRecyclerView(accountingItems);
                        // 設定總花費
                        int totalCost = 0;
                        for(AccountingItem item: accountingItems) {
                            totalCost = totalCost + item.getAmount();
                        }
                        financeView.initTxtTotalCost(totalCost);
                    }
                });
            }
        });
    }
}
