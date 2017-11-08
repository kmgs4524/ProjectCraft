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

public class SetRecyclerViewData {
    private FinanceView financeView;

    public SetRecyclerViewData(FinanceView view) {
        this.financeView = view;
    }

    public void setRecyclerViewData() {
        ReadUser readUser = new ReadUser();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                ReadTeamFinance readTeamFinance = new ReadTeamFinance();
                readTeamFinance.getAccountingItem(data.getTeamId(), new CallBack<ArrayList<AccountingItem>>() {
                    @Override
                    public void update(ArrayList<AccountingItem> data) {
                        financeView.initRecyclerView(data);
                    }
                });
            }
        });

    }
}
