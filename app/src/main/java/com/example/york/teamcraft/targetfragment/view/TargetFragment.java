package com.example.york.teamcraft.targetfragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.financefragment.viewmodel.SetFloatingButton;
import com.example.york.teamcraft.targetfragment.viewmodel.ConfirmContentTask;
import com.example.york.teamcraft.targetfragment.viewmodel.DeleteContentTask;
import com.example.york.teamcraft.targetfragment.viewmodel.SetButtonVisibility;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.viewmodel.CheckUserPosition;
import com.google.firebase.auth.FirebaseAuth;


public class TargetFragment extends Fragment {
    // received data
    private String groupId;
    private ContentTask contentTask;
    private String groupTaskName;
    // view
    private TextView txtTopic;
    private TextView txtContent;
    private TextView txtResponsible;
    private TextView txtDate;
    private TextView txtTime;
    private Button btnDel;
    private Button btnConfirm;
    // view model
    private DeleteContentTask deleteContentTask;
    private ConfirmContentTask confrimContentTask;
    private SetButtonVisibility setButtonVisibility;    // 負責判斷是否該顯示確認、刪除細項工作的Button

    // Factory Method: 傳入需要的參數，讓fragment在建構時便擁有contentTask的資料
    // 接收TaskFragment傳來groupName是為了刪除時所需用到的路徑
    public static TargetFragment newInstance(String groupId, String groupTaskName, ContentTask contentTask) {
        Bundle bundle = new Bundle();
        bundle.putString("groupId", groupId);
        bundle.putParcelable("contentTask", contentTask);
        bundle.putString("groupTaskName", groupTaskName);
        TargetFragment targetFragment = new TargetFragment();
        targetFragment.setArguments(bundle);

        return targetFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_target, container, false);
        // find view
        txtTopic = (TextView) view.findViewById(R.id.txt_card_item_grouptask_name);
        txtContent = (TextView) view.findViewById(R.id.txt_card_item_group_task_content);
        txtResponsible = (TextView) view.findViewById(R.id.txt_card_item_grouptask_respn_name);
        txtDate = (TextView) view.findViewById(R.id.txt_card_item_group_task_date);
        btnDel = (Button) view.findViewById(R.id.btn_delete_content_task);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm_content_task);
        // view model
        setButtonVisibility = new SetButtonVisibility(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        groupId = getArguments().getString("groupId");
        groupTaskName = getArguments().getString("groupTaskName");
        contentTask = (ContentTask) getArguments().get("contentTask");
        setButtonVisibility.setVisibility(groupId); // 根據收到的groupId判斷是否為自己的組別以及是否為該組的組長
        if(contentTask != null) {   // 第一次進入活動工作頁面時，contentTask會是null
            txtTopic.setText(contentTask.getTopic());
            txtContent.setText(contentTask.getContent());
            txtResponsible.setText(contentTask.getResponsible());
            txtDate.setText(contentTask.getDate());
            initBtnDel();
        }
    }

    public void initBtnDel() {
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContentTask = new DeleteContentTask();
                deleteContentTask.deleteTask(groupTaskName, contentTask.getTaskId());
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confrimContentTask = new ConfirmContentTask();
                confrimContentTask.confirmTask(groupTaskName, contentTask.getTaskId());
            }
        });
    }

    // 設定確認細項工作及刪除細項工作的Buttong是否顯示
    public void setBtnVisibility(boolean isVisible) {
        if(isVisible) {
            btnDel.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.VISIBLE);
        }
    }
}
