package com.example.york.teamcraft.financefragment.model;

import com.example.york.teamcraft.CallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by York on 2017/11/13.
 */

public class ReadTeamBudget {
    private static String TAG = "ReadTeamBudget";
    private DatabaseReference teamBudgetRef;

    public void getBudget(String teamId, final CallBack<Integer> callBack) {
        teamBudgetRef = FirebaseDatabase.getInstance().getReference().child("teamBudgets").child(teamId);
        teamBudgetRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int budget = dataSnapshot.getValue(Integer.class);
                callBack.update(budget);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
