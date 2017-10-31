package com.example.york.teamcraft.teammanage.taskprogress.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupMissionProgress;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/10/21.
 */

public class SetAdapterData {
    private ReadTeam readTeam;
    private ReadGroupTasks readGroupTasks;

    private ArrayList<GroupProgress> progList;

    public SetAdapterData() {
        this.readTeam = new ReadTeam();
        this.readGroupTasks = new ReadGroupTasks();
    }

    public void setData(final CallBack<ArrayList<GroupProgress>> callBack) {
        progList = new ArrayList<>();

        readTeam.getTeamGroupByDataChange(new CallBack<ArrayList<Group>>() {
            @Override
            public void update(ArrayList<Group> data) {
                Iterator<Group> iterator = data.iterator(); // 迭代出team中所有的group
                while (iterator.hasNext()) {
                    final Group nextGroup = iterator.next();

//                    Log.d("nextgroup", nextGroup.getName());
                    readGroupTasks.getAllContentTaskNum(nextGroup.getId(), new CallBack<GroupProgress>() {
                        @Override
                        public void update(GroupProgress data) {
                            data.setGroupName(nextGroup.getName());
                            boolean exist = false;
                            int index = 0;

                            if(progList.isEmpty()) {
                               progList.add(data);
                            } else {
                                for(int i = 0; i < progList.size(); i++) {
                                    if(progList.get(i).getGroupName().equals(nextGroup.getName())) {
                                        exist = true;
                                        index = i;
                                    }
                                }
                                if(exist) {
                                    progList.set(index, data);
                                    Log.d("nextgroup", "groupName: " + data.getGroupName() + " totalNum: " + data.getTotalTaskNum() + " checkedNum: " + data.getCheckedTaskNum());
                                } else {
                                    progList.add(data);
                                    Log.d("nextgroup", "groupName: " + data.getGroupName() + " totalNum: " + data.getTotalTaskNum() + " checkedNum: " + data.getCheckedTaskNum());
                                }
                            }

                            Log.d("nextgroup", Integer.toString(progList.size()));
                            callBack.update(progList);
                        }
                    });

                }
            }
        });
    }
}
