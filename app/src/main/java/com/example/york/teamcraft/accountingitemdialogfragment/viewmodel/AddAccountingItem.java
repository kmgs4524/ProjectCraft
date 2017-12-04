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

    public void addItem(final String name, final String strAmount, final String payer, final String date, final String type) {
        ReadUser readUser = new ReadUser();
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                WriteFinance writeFinance = new WriteFinance();
                int amount = 0;
                if(type.equals("cost")) {
                    amount = - Integer.parseInt(strAmount);
                } else {
                    amount = Integer.parseInt(strAmount);
                }
                writeFinance.pushData(data.getTeamId(), new AccountingItem(name, amount, date, payer, type));
            }
        });
    }
}
