package com.example.york.teamcraft.teammanage.model;

import com.example.york.teamcraft.teammanage.model.Team;
import com.example.york.teamcraft.teammanage.model.WriteDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/24.
 */

public class WriteTeam implements WriteDatabase {
    private DatabaseReference rootRef;
    private DatabaseReference teamsRef;
    private Map<String, Object> teamMap;    // 存放team object

    public WriteTeam() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        teamsRef = rootRef.child("teams");
        teamMap = new HashMap<>();
    }

    @Override
    public void updateData() {

    }

    @Override
    public void pushData(Map map) {
//        DatabaseReference newUserRef = usersRef.push();
        String key = rootRef.push().getKey();
        teamMap.put(key, new Team("春暉成果發表"));
        teamsRef.updateChildren(teamMap);

    }
}
