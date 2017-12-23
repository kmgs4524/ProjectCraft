package com.example.york.teamcraft.taskfragment.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupMissionProgress;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by York on 2017/10/11.
 */

public class ReadGroupTasks {
    private DatabaseReference groupTasksRef;
    private boolean exist = false;  // 在checkGroupTaskName用來檢查是否已存在相同的groupTaskName
//    private String groupId;

    public ReadGroupTasks() {
        groupTasksRef = FirebaseDatabase.getInstance().getReference().child("groupTasks");
    }

    public void checkGroupTaskName(String groupId, final String groupTaskName, final CallBack<Boolean> callBack) {
        DatabaseReference childRef = groupTasksRef.child(groupId);

        childRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (groupTaskName.equals(dataSnapshot.getKey())) {   // 若是已存在相同名稱的groupTask
                    exist = true;
                    callBack.update(exist);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // 取得群組任務的名稱及底下的細項工作
    public void getAllTask(String groupId, final CallBackTwoArgs<ArrayList<String>, HashMap<String, ArrayList<ContentTask>>> callBack) {
        final ArrayList<String> groupList = new ArrayList<>();  // 群組任務名稱的list
        final HashMap<String, ArrayList<ContentTask>> itemMap = new HashMap<>();    // 細項任務的map，key: 群組任務名稱, value: 細項任務的list

        DatabaseReference childRef = groupTasksRef.child(groupId);
        childRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String groupTaskName = dataSnapshot.getKey();
                // ArrayList為傳址，故無法重複使用
                ArrayList<ContentTask> contentTaskList = new ArrayList<>();   // 細項任務的list

                groupList.add(groupTaskName);
                ContentTask task;
                Iterator<DataSnapshot> childSnapShot = dataSnapshot.getChildren().iterator();
                while (childSnapShot.hasNext()) {
                    try {
                        task = childSnapShot.next().getValue(ContentTask.class);
                        contentTaskList.add(task);
                    } catch (Exception e) {
                        Log.d(e.toString(), e.getMessage());
                    }

                }

                itemMap.put(groupTaskName, contentTaskList);
                callBack.update(groupList, itemMap);
            }

            // 當groupId底下的某個child node發生改變時，便會呼叫此方法
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String groupTaskName = dataSnapshot.getKey();
                // ArrayList為傳址，故無法重複使用
                ArrayList<ContentTask> contentTaskList = new ArrayList<>();   // 細項任務的list

//                groupList.add(groupTaskName);
                ContentTask task;
                Iterator<DataSnapshot> childSnapShot = dataSnapshot.getChildren().iterator();
                while (childSnapShot.hasNext()) {
                    try {
                        task = childSnapShot.next().getValue(ContentTask.class);
                        contentTaskList.add(task);
                    } catch (Exception e) {
                        Log.d(e.toString(), e.getMessage());
                    }

                }

                itemMap.put(groupTaskName, contentTaskList);
                callBack.update(groupList, itemMap);
            }

            // 當groupId底下的某個活動名稱節點被完全刪除時，才會呼叫此方法
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String groupTaskName = dataSnapshot.getKey();
                // ArrayList為傳址，故無法重複使用
                ArrayList<ContentTask> contentTaskList = new ArrayList<>();   // 細項任務的list

                groupList.remove(groupList.indexOf(groupTaskName)); // 將被刪除的群組名稱從groupList中移除
                callBack.update(groupList, itemMap);    // 更新groupList
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getAllTaskByValueEvent(String groupId, final CallBackTwoArgs<ArrayList<String>, HashMap<String, ArrayList<ContentTask>>> callBack) {
        final ArrayList<String> groupTaskNameList = new ArrayList<>();  // 群組任務名稱的list
        final HashMap<String, ArrayList<ContentTask>> itemMap = new HashMap<>();    // 細項任務的map，key: 群組任務名稱, value: 細項任務的list

        DatabaseReference childRef = groupTasksRef.child(groupId);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> groupTaskIterator = dataSnapshot.getChildren().iterator();
                while (groupTaskIterator.hasNext()) {
                    DataSnapshot groupTaskNameShot = groupTaskIterator.next();   // 群組名稱的節點
                    groupTaskNameList.add(groupTaskNameShot.getKey());

                    ArrayList<ContentTask> contentTaskList = new ArrayList<>();   // 細項工作的list
                    Iterator<DataSnapshot> contentTaskIterator = groupTaskNameShot.getChildren().iterator();
                    while (contentTaskIterator.hasNext()) {
                        DataSnapshot contentTaskShot = contentTaskIterator.next();  // 細項工作的節點
                        ContentTask contentTask = contentTaskShot.getValue(ContentTask.class);
                        contentTaskList.add(contentTask);
                    }
                    itemMap.put(groupTaskNameShot.getKey(), contentTaskList);
                }
                callBack.update(groupTaskNameList, itemMap);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // 需要groupId, responId，取得個人被分派工作的taskIdList, taskList
    public void getPersonalTask(final String responId, final CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>> callBack) {

//        DatabaseReference groupIdRef = groupTasksRef.child(groupId).getRef();
        groupTasksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot groupTasksSnapshot) {
                final ArrayList<DataPath> dataPaths = new ArrayList<>(); // DataPath List : 負責存放 groupId, groupTaskName, taskId
                final ArrayList<ContentTask> contentTasks = new ArrayList<>();  // GroupTask List
                for(DataSnapshot groupIdSnapShot: groupTasksSnapshot.getChildren()) {
                    for(DataSnapshot groupTaskSnapShot: groupIdSnapShot.getChildren()) {
                        for(DataSnapshot contentTaskSnapShot: groupTaskSnapShot.getChildren()) {
                            ContentTask contentTask = contentTaskSnapShot.getValue(ContentTask.class);
                            if(contentTask.getResponId().equals(responId)) {
                                contentTasks.add(contentTask);
                                DataPath dataPath = new DataPath(groupIdSnapShot.getKey(), groupTaskSnapShot.getKey(), contentTaskSnapShot.getKey());
                                dataPaths.add(dataPath);
                            }
                        }
                    }
//                    while (groupTaskIterator.hasNext()) {
//                        DataSnapshot contentTaskSnapShot = groupTaskIterator.next();
//                        ContentTask contentTask = contentTaskSnapShot.getValue(ContentTask.class);
//                        if (responId.equals(contentTask.getResponId())) {
//                            contentTasks.add(contentTask);
//                            DataPath dataPath = new DataPath(groupId, groupTaskSnapShot.getKey(), contentTaskSnapShot.getKey());
//                            dataPaths.add(dataPath);
//                        }
//                    }
                }
//                Log.d("onDataChange", "cotentTasks size: " + contentTasks.size());
                callBack.update(dataPaths, contentTasks);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getAllContentTaskNum(final String groupId, final CallBack<GroupProgress> callBack) {
        DatabaseReference childRef = groupTasksRef.child(groupId);
        final int[] totalNum = {0}; // 所有細項工作的數量
        final int[] checkedNum = {0};   // status為checked的數量
        final GroupProgress groupProgress = new GroupProgress(groupId, totalNum[0], checkedNum[0]);
//        Log.d("next", childRef.getKey());
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("next", dataSnapshot.getKey());   // 群組名稱
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();    // 擁有群組任務中每個細項活動的iterator
                while (iterator.hasNext()) {    // 迭代出每個群組任務
                    DataSnapshot groupTaskSnapShot = iterator.next();
                    Iterator<DataSnapshot> contentTaskIterator = groupTaskSnapShot.getChildren().iterator();
                    while (contentTaskIterator.hasNext()) {
                        DataSnapshot contentTaskSnapShot = contentTaskIterator.next();
                        totalNum[0] = totalNum[0] + 1;
                        if (contentTaskSnapShot.child("status").getValue().equals("checked")) {
                            checkedNum[0] = checkedNum[0] + 1;
                        }
                    }
                }
                groupProgress.setTotalTaskNum(totalNum[0]);
                groupProgress.setCheckedTaskNum(checkedNum[0]);
                callBack.update(groupProgress);
                totalNum[0] = 0;
                checkedNum[0] = 0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
