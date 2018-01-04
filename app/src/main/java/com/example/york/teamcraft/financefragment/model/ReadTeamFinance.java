package com.example.york.teamcraft.financefragment.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/11/5.
 */

public class ReadTeamFinance {
    private static String TAG = "ReadTeamFinance";
    private DatabaseReference teamFinaneRef;

    public void getAccountingItem(String teamId, final CallBack<ArrayList<AccountingItem>> callBack) {
        teamFinaneRef = FirebaseDatabase.getInstance().getReference().child("teamFinance").child(teamId);
        teamFinaneRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AccountingItem> accountingItems = new ArrayList<>();
                for(DataSnapshot accountingItemSnapShot: dataSnapshot.getChildren()) {
                    AccountingItem item = accountingItemSnapShot.getValue(AccountingItem.class);
                    Log.d(TAG, "onDataChange: item name: " + item.getName());
                    accountingItems.add(item);
                }
                callBack.update(accountingItems);
                Log.d(TAG, "onDataChange: items size: " + accountingItems.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getCostItem(String teamId, final CallBack<ArrayList<AccountingItem>> callBack) {
        teamFinaneRef = FirebaseDatabase.getInstance().getReference().child("teamFinance").child(teamId);
        teamFinaneRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AccountingItem> itemList = new ArrayList<>();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot nextSnapShot = iterator.next();
                    String type = nextSnapShot.child("type").getValue(String.class);
                    if(type.equals("cost")) {
                        AccountingItem item = nextSnapShot.getValue(AccountingItem.class);
                        itemList.add(item);
                    }
                }
                Log.d(TAG, "onDataChange: " + itemList.size());
                callBack.update(itemList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
