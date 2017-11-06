package com.example.york.teamcraft.finance.model;

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

    private DatabaseReference teamFinanRef;

    public void getAccountingItem(String teamId, final CallBack<ArrayList<AccountingItem>> callBack) {
        teamFinanRef = FirebaseDatabase.getInstance().getReference().child("teamFinance").child(teamId);
        teamFinanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AccountingItem> itemlist = new ArrayList<>();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot nextSnapShot = iterator.next();
                    AccountingItem item = nextSnapShot.getValue(AccountingItem.class);
                    itemlist.add(item);
                }
                Log.d(TAG, "onDataChange: " + itemlist.size());
                callBack.update(itemlist);
//                itemlist.clear();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
