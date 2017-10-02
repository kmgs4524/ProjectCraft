package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

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
        Task<User> userTask = readUser.getUserData();

        userTask.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                DatabaseReference childRef = teamActRef.child(user.getTeamId()).getRef();
                Log.d(TAG, childRef.getKey());

                childRef.push().updateChildren(map);
            }
        });

    }

}
