package com.example.york.teamcraft.teammanage.groupmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.model.Group;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */
//群組管理分頁
public class GroupManageFragment extends Fragment {
    private static final String TAG = "GroupManageFragment";

    //存放Drawable的list
    private  ArrayList<Group> groupList = new ArrayList<>();

    //介面元件
    private GridView gridView;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_manage_group, container, false);

        initGrid(view);
        initFab(view);
        return view;
    }

    //初始化Floating Button
    private void initFab(View view){
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getContext(), CreateGroupActivity.class);
                startActivity(intent);
                //addImg();
            }
        });
    }

    //初始化GridView
    private  void initGrid(View view) {
        gridView = (GridView) view.findViewById(R.id.gridView); // link GridView
        groupList.add(new Group("會資小組", "4"));
        groupList.add(new Group("D1306寢聚小組", "4"));
        groupList.add(new Group("器材組", "4"));
        groupList.add(new Group("活動組", "4"));
        gridView.setAdapter(new GridItemAdapter(getActivity(), groupList));
        Log.d(TAG, "setAdapter");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), GroupActivity.class);
                startActivity(intent);  // 進入CreateGroupActivity
            }
        });
    }

    //按下Floating Button後新增drawable
    private  void addImg(){
//      RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        Integer[] arr = {R.drawable.img, R.drawable.img2};

//        drawableList.add(R.drawable.img2);  //新增Drawable img2到list
//        gridView.setAdapter(new ImageAdapter(getContext(), drawableList));
//        Log.d(TAG, String.valueOf(gridView));
    }
}
