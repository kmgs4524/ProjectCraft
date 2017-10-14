package com.example.york.teamcraft.teammanage.creategroup.model;

import android.provider.Telephony;
import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.member.Member;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/10/14.
 */

public class ReadTeamMember {
    private DatabaseReference teamMemRef;

    public ReadTeamMember() {
        teamMemRef = FirebaseDatabase.getInstance().getReference().child("teamMembers");
    }

    public void getMember(String teamId, final CallBack<ArrayList<Member>> callBack) {
        final ArrayList<Member> memList = new ArrayList<>();

        teamMemRef.child(teamId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
//                    Log.d("member data", iterator.next().toString());
                    Member member = iterator.next().getValue(Member.class);
                    memList.add(member);
                }
                callBack.update(memList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
