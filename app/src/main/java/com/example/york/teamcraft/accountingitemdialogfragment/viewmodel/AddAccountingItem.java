package com.example.york.teamcraft.accountingitemdialogfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.accountingitemdialogfragment.model.WriteFinance;
import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/11/10.
 */

public class AddAccountingItem {
    // model
    private WriteFinance writeFinance;
    private ReadUser readUser;

    public void addItem(final String name, final String amount, final String payer, final String date, final String type) {
        ReadUser readUser = new ReadUser();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                WriteFinance writeFinance = new WriteFinance();
                writeFinance.pushData(data.getTeamId(), new AccountingItem(name, Integer.parseInt(amount), date, payer, type));
            }
        });
    }
}
