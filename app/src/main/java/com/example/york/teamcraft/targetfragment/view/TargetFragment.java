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
import com.example.york.teamcraft.targetfragment.viewmodel.ConfirmContentTask;
import com.example.york.teamcraft.targetfragment.viewmodel.DeleteContentTask;
import com.example.york.teamcraft.taskfragment.model.ContentTask;


public class TargetFragment extends Fragment {
    // received data
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

    // Factory Method: 傳入需要的參數，讓fragment在建構時便擁有contentTask的資料
    // 接收TaskFragment傳來groupName是為了刪除時所需用到的路徑
    public static TargetFragment newInstance(String groupTaskName, ContentTask contentTask) {
        Bundle bundle = new Bundle();
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
        txtContent = (TextView) view.findViewById(R.id.txt_card_item_grouptask_content);
        txtResponsible = (TextView) view.findViewById(R.id.txt_card_item_grouptask_respn_name);
        txtDate = (TextView) view.findViewById(R.id.txt_card_item_grouptask_date);
        txtTime = (TextView) view.findViewById(R.id.txt_card_item_grouptask_time);
        btnDel = (Button) view.findViewById(R.id.btn_delete_content_task);
        btnConfirm = (Button) view.findViewById(R.id.btn_confirm_content_task);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        groupTaskName = getArguments().getString("groupTaskName");
        contentTask = (ContentTask) getArguments().get("contentTask");
        if(groupTaskName != null) {
            Log.d("argu", groupTaskName);
        }

        if(contentTask != null) {   // 第一次進入活動工作頁面時，contentTask會是null
            txtTopic.setText(contentTask.getTopic());
            txtContent.setText(contentTask.getContent());
            txtResponsible.setText(contentTask.getResponsible());
            txtDate.setText(contentTask.getDate());
            txtTime.setText(contentTask.getTime());
//            Log.d("argu", contentTask.getTopic());
//            Log.d("argu", contentTask.getResponsible());
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
//                Log.d("argu", groupTaskName);
//                Log.d("argu", contentTask.getTaskId());
                confrimContentTask = new ConfirmContentTask();
                confrimContentTask.confirmTask(groupTaskName, contentTask.getTaskId());
            }
        });
    }
}
