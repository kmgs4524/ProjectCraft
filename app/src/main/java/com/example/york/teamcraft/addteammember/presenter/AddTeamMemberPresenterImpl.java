package com.example.york.teamcraft.addteammember.presenter;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.addteammember.view.AddTeamMemberView;
import com.example.york.teamcraft.addteammember.viewmodel.AddTeamMember;
import com.example.york.teamcraft.addteammember.viewmodel.GetUserData;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.model.WriteTeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/19.
 */

public class AddTeamMemberPresenterImpl implements AddTeamMemberPresenter{
    // view
    private AddTeamMemberView addTeamMemberView;
    // model
    private GetUserData getUserData;
    private AddTeamMember addTeamMember;
//    private ReadUser readUser;
//    private ReadTeamMember readTeamMember;
//    private WriteTeamMember writeTeamMember;    // teamID, teamMemberUserID, teamMemberUserName

    public AddTeamMemberPresenterImpl(AddTeamMemberView view) {
        this.addTeamMemberView = view;
    }

    @Override
    public void addTeamMember(final String email) {
        getUserData = new GetUserData();
        addTeamMember = new AddTeamMember(addTeamMemberView);
        getUserData.getTeamId(new CallBack<String>() {
            @Override
            public void update(final String teamId) {
                getUserData.getMemberUserIdAndData(email, new CallBackTwoArgs<String, User>() {
                    @Override
                    public void update(String userId, User user) {
                        Log.d("addTeamMember", "update: " + userId);
                        if(!(userId.equals("none"))) {
                            addTeamMember.addMember(teamId, userId, user.getName()); // include readTeamMember
                        } else {
                            addTeamMemberView.showFailMessage();
                        }
                    }
                });
            }
        });
    }

}
