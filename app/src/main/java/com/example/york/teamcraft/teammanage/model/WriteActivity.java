package com.example.york.teamcraft.teammanage.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class WriteActivity implements WriteDatabase{
    private DatabaseReference rootRef;
    private DatabaseReference rootRef;

    @Override
    public void updateData() {
        rootRef= FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void pushData(Map map) {

    }

}
