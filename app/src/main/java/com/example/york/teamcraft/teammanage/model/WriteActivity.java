package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class WriteActivity implements WriteDatabase{
    private static String TAG = "WriteActivity";

    private DatabaseReference teamActRef;
    private ReadUser readUser;

    public WriteActivity() {
        teamActRef= FirebaseDatabase.getInstance().getReference().child("teamActivities");
        readUser = new ReadUser();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void pushData(final Map map) {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                DatabaseReference childRef = teamActRef.child(data.getTeamId()).getRef();
                Log.d(TAG, childRef.getKey());

                childRef.push().updateChildren(map);
            }
        });
    }

}
