package com.example.york.teamcraft.modifysearchiddialogfragment;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.model.WriteTeam;

/**
 * Created by York on 2017/12/12.
 */

public class ModifySearchId {
    private WriteTeam writeTeam;
    private ReadUser readUser;
    private ReadTeam readTeam;

    public void modifySearchId(final String searchId) {
        readUser = new ReadUser();
        readTeam = new ReadTeam();
        writeTeam = new WriteTeam();

        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                writeTeam.changeSearchId(user.getTeamId(), searchId);
            }
        });
    }
}
