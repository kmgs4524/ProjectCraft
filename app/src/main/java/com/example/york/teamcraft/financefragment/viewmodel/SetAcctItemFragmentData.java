package com.example.york.teamcraft.financefragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.financefragment.model.ReadTeamFinance;
import com.example.york.teamcraft.financefragment.view.FinanceView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/10.
 */

public class SetAcctItemFragmentData {
    // model
    private ReadUser readUser;
    private ReadTeamFinance readTeamFinance;
    // view
    private FinanceView financeView;

    public SetAcctItemFragmentData(FinanceView view) {
        this.financeView = view;
    }

    public void setFragmentData(AccountingItem item) {
        financeView.showAcctItemDialogFragment(item);
    }
}
