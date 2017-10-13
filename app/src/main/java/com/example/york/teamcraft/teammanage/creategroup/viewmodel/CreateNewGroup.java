package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import com.example.york.teamcraft.teammanage.creategroup.model.WriteTeamGroup;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by York on 2017/10/13.
 */

public class CreateNewGroup {
    // view
    private CreateGroupView createGroupView;
    // model
    private ReadUser readUser;
    private WriteTeamGroup writeTeamGroup;

    public CreateNewGroup(CreateGroupView view) {
        this.createGroupView = view;
        this.readUser = new ReadUser();
        this.writeTeamGroup = new WriteTeamGroup();
    }

    public void create(final String groupName) {
        Task<User> task = readUser.getUserData();
        task.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                writeTeamGroup.pushData(user.getTeamId(), groupName);
                createGroupView.finishCreate();
            }
        });
    }

}
