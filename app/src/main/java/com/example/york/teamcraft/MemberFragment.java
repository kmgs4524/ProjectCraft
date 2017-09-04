package com.example.york.teamcraft;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.york.teamcraft.teammanage.GroupManageFragment;

import java.util.ArrayList;

public class MemberFragment extends Fragment {
    private static final String TAG = "GroupManageFragment";

    //存放Drawable的list
    private ArrayList<Integer> drawableList = new ArrayList<>();

    //介面元件
    private GridView gridView;
    private FloatingActionButton fab;

    public MemberFragment() {
        // 當Fragment被銷毀並重新創造時，會自動呼叫這個無參數的建構子
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_groupmanage_fragment, container, false);
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
                addImg();
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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ImageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Integer> drawableList;

        public  ImageAdapter(Context c, ArrayList list) {
            context = c;
            drawableList = list;
        }

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
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(drawableList.get(position));
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
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

}
