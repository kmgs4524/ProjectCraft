package com.example.york.teamcraft.teammanage.model;

import com.example.york.teamcraft.teammanage.model.Team;
import com.example.york.teamcraft.teammanage.model.WriteDatabase;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/24.
 */

public class WriteTeam {
    private DatabaseReference teamsRef;
    private Map<String, Object> teamMap;    // 存放team object

    public WriteTeam() {
        teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");
        teamMap = new HashMap<>();
    }

    public Task<String> pushData(String teamName) {
//        DatabaseReference newUserRef = usersRef.push();
        TaskCompletionSource dbSource = new TaskCompletionSource();

        String key = teamsRef.push().getKey();
        dbSource.setResult(key);
        teamMap.put(key, new Team(teamName));
        teamsRef.updateChildren(teamMap);

        return dbSource.getTask();
    }

}
