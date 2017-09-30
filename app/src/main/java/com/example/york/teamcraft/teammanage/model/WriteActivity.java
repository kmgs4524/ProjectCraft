package com.example.york.teamcraft.teammanage.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class WriteActivity implements WriteDatabase{
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
    public void pushData(Map map) {

    }

}
