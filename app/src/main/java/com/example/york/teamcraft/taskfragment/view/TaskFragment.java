package com.example.york.teamcraft.taskfragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.grouptaskfragment.GroupTaskDialogFragment;
import com.example.york.teamcraft.grouptaskfragment.ConfirmClickListener;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.taskfragment.presenter.TaskFragmentPresenter;
import com.example.york.teamcraft.taskfragment.presenter.TaskFragmentPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskFragment extends Fragment implements TaskFragmentView {
    // 傳進TaskFragment的groupId
    private String groupId;
    // view
    @BindView(R.id.img_group_task) ImageView imgAdd;
    @BindView(R.id.expand_list) ExpandableListView expandListView;
    private ExpandableListAdapter adapter;
    // Database Model
    private ReadGroupTasks readGroupTasks;
    // presenter
    private TaskFragmentPresenter taskFragmentPresenter;
    // passdata Listener 用來傳送資料MyActivity的CallBack
    private PassDataListener callback;

    // Factory Method: 傳入需要的參數，讓fragment在建構時便擁有需要的參數
    public static TaskFragment newInstance(String groupId) {
        TaskFragment taskFragment = new TaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("groupId", groupId);
        taskFragment.setArguments(bundle);
        return taskFragment;
    }

    // 在與container activity連接時便指定他為負責傳遞資料的callback，故container activity需實作傳遞資料的方法
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (PassDataListener) context;
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_task, container, false);
        ButterKnife.bind(this, view);
        // get passed groupId
        groupId = getArguments().getString("groupId");
        // set ExpandableList and Adapter
        setExpandList();
        // 先檢查使用者的group id及position，再決定是否呼叫setImgAddGroupTaskListener()以顯示新增群組工作的按鈕
        taskFragmentPresenter = new TaskFragmentPresenterImpl(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        taskFragmentPresenter.checkUserGroup(groupId);
    }

    // 設定新增群組任務的加號按鈕
    public void setImgAddGroupTaskListener() {
        imgAdd.setVisibility(View.VISIBLE);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show AddGroupTaskDialog
                taskFragmentPresenter.showAddGroupTaskDialog(groupId);
            }
        });
    }

    // 顯示新增群組任務的選單
    @Override
    public void showAddGroupTaskDialog(ConfirmClickListener callbackListener) {
        GroupTaskDialogFragment groupTaskDialogFragment = GroupTaskDialogFragment.newInstance(callbackListener);
        groupTaskDialogFragment.show(getFragmentManager(), "add groupTask");
    }

    // 設定下拉式選單
    public void setExpandList() {
        readGroupTasks = new ReadGroupTasks();
        readGroupTasks.getAllTask(groupId, new CallBackTwoArgs< ArrayList<String>, HashMap<String, ArrayList<ContentTask>> >() {
            @Override
            public void update(final ArrayList<String> groupTasks, final HashMap<String, ArrayList<ContentTask>> map) {
                // init adapter
                adapter = new ExpandableListAdapter(getActivity(), groupTasks, map, groupId);
                // set adapter
                expandListView.setAdapter(adapter);
                // set listener
                expandListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {  // 設定點擊細項工作後，將content task傳送到TargetFragment
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        ContentTask contentTask = map.get(groupTasks.get(groupPosition)).get(childPosition);
                        callback.passData(groupTasks.get(groupPosition), contentTask);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        taskFragmentPresenter = null;
    }
}
