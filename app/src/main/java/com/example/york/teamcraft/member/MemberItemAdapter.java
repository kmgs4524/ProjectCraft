package com.example.york.teamcraft.member;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/7.
 */

public class MemberItemAdapter extends RecyclerView.Adapter<MemberViewHolder>{
//    private Context context;
    private ArrayList<Member> dataList;

    public MemberItemAdapter(ArrayList<Member> list) {
       dataList = list;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_member, null);

        MemberViewHolder holder = new MemberViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        holder.getTxtName().setText(dataList.get(position).getName());
//        holder.getTxtPos().setText(dataList.get(position).getPosition());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
