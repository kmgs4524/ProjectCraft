package com.example.york.teamcraft.addteammember.viewmodel;

import com.example.york.teamcraft.addteammember.view.AddTeamMemberView;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.model.WriteTeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by York on 2017/12/19.
 */

public class AddTeamMember {
    private AddTeamMemberView addTeamMemberView;
    private WriteTeamMember writeTeamMember;

    public AddTeamMember(AddTeamMemberView view) {
        this.addTeamMemberView = view;
    }

    public void addMember(String teamId, String memberUserId, String memberName) {
        writeTeamMember = new WriteTeamMember();
        writeTeamMember.updateTeamMember(teamId, memberUserId, memberName).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                addTeamMemberView.showSuccessfulMessage();
            }
        });
    }
}
