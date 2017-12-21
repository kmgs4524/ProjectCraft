package com.example.york.teamcraft.financefragment.viewmodel;

import com.example.york.teamcraft.financefragment.view.FinanceView;

/**
 * Created by York on 2017/11/6.
 */

public class SetFloatingButton {
    private FinanceView financeView;

    public SetFloatingButton(FinanceView view) {
        this.financeView = view;
    }

    public void setFinanceView() {
        financeView.initFloatingButton();
    }
}
