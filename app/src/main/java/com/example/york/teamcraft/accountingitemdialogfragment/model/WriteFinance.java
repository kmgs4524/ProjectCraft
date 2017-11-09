package com.example.york.teamcraft.accountingitemdialogfragment.model;

import com.example.york.teamcraft.financefragment.model.AccountingItem;
import com.example.york.teamcraft.teammanage.model.Team;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by York on 2017/11/10.
 */

public class WriteFinance {
    private DatabaseReference financeRef;
    private Map<String, Object> financeMap;    // 存放team object

    public void pushData(String teamId, AccountingItem item) {
        financeRef = FirebaseDatabase.getInstance().getReference().child("teamFinance");
        financeMap = new HashMap<String, Object>();
        String key = financeRef.push().getKey();

        financeMap.put(key, item);
        financeRef.child(teamId).updateChildren(financeMap);
    }
}
