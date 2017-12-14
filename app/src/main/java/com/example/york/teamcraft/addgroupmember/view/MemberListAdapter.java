package com.example.york.teamcraft.addgroupmember.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by York on 2017/12/14.
 */

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>{
    private ArrayList<TeamMember> teamMembers;
    private Context context;

    public MemberListAdapter(ArrayList<TeamMember> teamMembers, Context context) {
        this.teamMembers = teamMembers;
        this.context = context;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_team_member, parent, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(itemView);
        return memberViewHolder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        holder.setCirImgPortrait(teamMembers.get(position).getPhotoUrl());
        holder.setTxtName(teamMembers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return teamMembers.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView cirImgPortrait;
        private TextView txtName;
        private CheckBox checkBox;

        public MemberViewHolder(View itemView) {
            super(itemView);
            cirImgPortrait = (CircleImageView) itemView.findViewById(R.id.cir_img_member_portrait);
            txtName = (TextView) itemView.findViewById(R.id.txt_team_member_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_team_member);
        }

        public void setCirImgPortrait(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .into(cirImgPortrait);
        }

        public void setTxtName(String name) {
            txtName.setText(name);
        }
    }
}
