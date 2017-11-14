package com.example.york.teamcraft.financefragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.financefragment.model.ReadTeamBudget;
import com.example.york.teamcraft.financefragment.model.ReadTeamFinance;
import com.example.york.teamcraft.financefragment.view.FinanceView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/13.
 */

public class SetBudget {
    // model
    ReadTeamBudget readTeamBudget;
    ReadTeamFinance readTeamFinance;
    ReadUser readUser;
    // view
    FinanceView financeView;

    public SetBudget(FinanceView view) {
        this.financeView = view;
    }

    public void setData() {
        readUser = new ReadUser();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(final User user) {
                readTeamBudget = new ReadTeamBudget();
                readTeamBudget.getBudget(user.getTeamId(), new CallBack<Integer>() {
                    @Override
                    public void update(final Integer budget) {
                        // 設定總預算金額
                        financeView.initTxtBuget(budget);
                        // 設定ProgressWheel的數值
                        readTeamFinance = new ReadTeamFinance();
                        readTeamFinance.getCostItem(user.getTeamId(), new CallBack<ArrayList<AccountingItem>>() {
                            @Override
                            public void update(ArrayList<AccountingItem> costItemList) {
                                int totalCost = 0;
                                for(AccountingItem costItem: costItemList) {
                                    totalCost = totalCost + costItem.getAmount();
                                }
                                financeView.initProgressBar(budget, totalCost);
                            }
                        });
                    }
                });
            }
        });
    }
}
