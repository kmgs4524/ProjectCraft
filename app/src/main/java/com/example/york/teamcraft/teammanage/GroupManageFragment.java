package com.example.york.teamcraft.teammanage;

import android.content.Context;
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
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.york.teamcraft.CreateGroupActivity;
import com.example.york.teamcraft.GroupActivity;
import com.example.york.teamcraft.R;
import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */
//群組管理分頁
public class GroupManageFragment extends Fragment {
    private static final String TAG = "GroupManageFragment";

    //存放Drawable的list
    private  ArrayList<Integer> drawableList = new ArrayList<>();

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
//        Integer[] draw = {R.drawable.img};
        drawableList.add(R.drawable.img);
        gridView.setAdapter(new ImageAdapter(getContext(), drawableList));
        Log.d(TAG, "setAdapter");

        //
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("DATA_GROUP_NAME", "宅宅笑嘻嘻");
                intent.putExtras(bundle);
                intent.setClass(getContext(), GroupActivity.class);
                startActivity(intent);  // 進入CreateGroupActivity
            }
        });
    }

    // GridView的Adapter，存放圖片
    private class ImageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Integer> drawableList;

        public  ImageAdapter(Context c, ArrayList list) {
            context = c;
            drawableList = list;
        }

        // 會被GirdView呼叫，return null的話不會顯示任何東西
        // How many items are in the data set represented by this Adapter.
        @Override
        public int getCount() {
            return drawableList.size();
        }

        // not needed
        @Override
        public Object getItem(int position) {
            return null;
        }

        // not needed
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);   //顯示drawable的View
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(drawableList.get(position)); // 設定ImageView來源為drawable檔案(圖片檔)
            return imageView;
        }

    }

    //按下Floating Button後新增drawable
    private  void addImg(){
//      RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        Integer[] arr = {R.drawable.img, R.drawable.img2};
        drawableList.add(R.drawable.img2);  //新增Drawable img2到list
        gridView.setAdapter(new ImageAdapter(getContext(), drawableList));
        Log.d(TAG, String.valueOf(gridView));
    }
}
