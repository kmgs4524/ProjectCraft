package com.example.york.teamcraft.taskfragment.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

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
                if(groupTaskName.equals(dataSnapshot.getKey())) {   // 若是已存在相同名稱的groupTask
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
        DatabaseReference childRef = groupTasksRef.child(groupId);
        final ArrayList<String> groupList = new ArrayList<>();  // 群組任務名稱的list
        final HashMap<String, ArrayList<ContentTask>> itemMap = new HashMap<>();    // 細項任務的map，key: 群組任務名稱, value: 細項任務的list

        childRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String groupTaskName = dataSnapshot.getKey();
                // ArrayList為傳址，故無法重複使用
                ArrayList<ContentTask> contentTaskList = new ArrayList<>();   // 細項任務的list

                groupList.add(groupTaskName);
                ContentTask task;
                Iterator<DataSnapshot> childSnapShot= dataSnapshot.getChildren().iterator();
                while (childSnapShot.hasNext()) {
                    task = childSnapShot.next().getValue(ContentTask.class);
                    contentTaskList.add(task);
                }

                itemMap.put(groupTaskName, contentTaskList);
                callBack.update(groupList, itemMap);
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

    // 需要groupId, responId，取得個人被分派工作的taskIdList, taskList
    public void getPersonalTask(final String groupId, final String responId, final CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>> callBack) {
        final ArrayList<ContentTask> taskList = new ArrayList<>();  // GroupTask List
        final ArrayList<DataPath> pathList = new ArrayList<>(); // DataPath List : 負責存放 groupId, groupTaskName, taskId

        DatabaseReference groupRef = groupTasksRef.child(groupId);    // groupId node
        groupRef.addChildEventListener(new ChildEventListener() {   // 迭代出某一群組底下的各個群組活動
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();    // 擁有群組活動中的每個細項活動iterator
                while (iterator.hasNext()) {    // 迭代出每個細項活動
                    DataSnapshot nextSnapShot = iterator.next();    // nextSnapShot為"key: taskId"的節點
//                    DataPath dataPath = new DataPath(groupId, , nextSnapShot.getKey());
                    ContentTask task = nextSnapShot.getValue(ContentTask.class);
//                    ContentTask task = iterator.next().getValue(ContentTask.class);
                    if(task.getResponId().equals(responId)) {
                        Log.d("wanted data", groupId + " " + dataSnapshot.getKey() + " " + nextSnapShot.getKey());  // 確認取得的資料無誤
                        DataPath dataPath = new DataPath(groupId, dataSnapshot.getKey(), nextSnapShot.getKey());
                        taskList.add(task);
                        pathList.add(dataPath);
                    }
                }
                callBack.update(pathList, taskList);
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

}
