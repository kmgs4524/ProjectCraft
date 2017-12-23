package com.example.york.teamcraft.schedulefragment.model;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.schedulefragment.data.RowData;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by York on 2017/10/28.
 */

public class GetTableData {
    // database model
    private ReadUser readUser;
    private ReadTeam readTeam;
    private ReadGroupTasks readGroupTasks;
    // collection
    private ArrayList<RowData> rowList;
    private HashMap<String, ArrayList<RowData>> map;

    public void getData(final CallBack<ArrayList<RowData>> callBack) {
        readUser = new ReadUser();
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeam = new ReadTeam();
                readTeam.getTeamGroupByDataChange(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                    @Override
                    public void update(ArrayList<Group> groups) {
                        readGroupTasks = new ReadGroupTasks();
                        for(final Group group: groups) {
                            readGroupTasks.getAllTaskByValueEvent(group.getId(), new CallBackTwoArgs<ArrayList<String>, HashMap<String, ArrayList<ContentTask>>>() {
                                @Override
                                public void update(ArrayList<String> groupTasks, HashMap<String, ArrayList<ContentTask>> stringArrayListHashMap) {
                                    setRowData(group.getName(), groupTasks, stringArrayListHashMap, callBack);
                                }
                            });
                        }
                    }
                });
            }
        });

    }

    public void setRowData(String groupName, ArrayList<String> list, HashMap<String, ArrayList<ContentTask>> stringArrayListHashMap, CallBack<ArrayList<RowData>> callBack) {
        rowList = new ArrayList<>();

        for(String groupTaskName: list) {   // 取出所有groupTaskName
            Iterator<ContentTask> iterator = stringArrayListHashMap.get(groupTaskName).iterator();  // 取得所有groupTaskName底下的ContentTask
            while (iterator.hasNext()) {
                ContentTask contentTask = iterator.next();
                RowData rowData = new RowData(groupName, contentTask.getTopic(), contentTask.getResponsible(), contentTask.getDate());
                rowList.add(rowData);
            }
        }
        callBack.update(rowList);
    }

}
