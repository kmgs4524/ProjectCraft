package com.example.york.teamcraft.memberfragment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.memberfragment.data.SectionOrItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by York on 2017/12/1.
 */

public class MemberListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_SECTION = 0;
    private static final int TYPE_ITEM = 1;
    private Context context;
    private ArrayList<SectionOrItem> sectionOrItems;

    public MemberListAdapter(Context context, ArrayList<SectionOrItem> sectionOrItems){
        this.context = context;
        this.sectionOrItems = sectionOrItems;
    }

    @Override
    public int getItemViewType(int position) {
        SectionOrItem sectionOrItem = sectionOrItems.get(position);
        if(sectionOrItem.isItem()){
            return TYPE_ITEM;
        } else {
            return TYPE_SECTION;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0) {
            Log.d("onCreateViewHolder", "is section");
            View sectionView = LayoutInflater.from(context).inflate(R.layout.recycler_view_section_group, parent, false);
            SectionViewHolder sectionHolder = new SectionViewHolder(sectionView);
            return  sectionHolder;
        } else {
            Log.d("onCreateViewHolder", "is item");
            View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_group_member, parent, false);
            ItemViewHolder itemHolder = new ItemViewHolder(itemView);
            return  itemHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 連接資料時，先檢查sectionOr
        if(sectionOrItems.get(position).isItem()) {
            Log.d("onBindViewHolder", "is item");
            Log.d("onBindViewHolder", "name: " + sectionOrItems.get(position).getMemberName());
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.setItemName(sectionOrItems.get(position).getMemberName());
            ((ItemViewHolder) holder).setItemPortrait(sectionOrItems.get(position).getImageUrl());
            ((ItemViewHolder) holder).setItemName(sectionOrItems.get(position).getMemberName());
            ((ItemViewHolder) holder).setItemEmail(sectionOrItems.get(position).getEmail());
            ((ItemViewHolder) holder).setItemPosition(sectionOrItems.get(position).getPosition());
        } else {
            Log.d("onBindViewHolder", "is section");
            ((SectionViewHolder) holder).setSectionName(sectionOrItems.get(position).getGroupName());
        }
    }

    @Override
    public int getItemCount() {
        return sectionOrItems.size();
    }

    class SectionViewHolder extends RecyclerView.ViewHolder{
        private TextView txtSectionName;

        public SectionViewHolder(View itemView) {
            super(itemView);
            txtSectionName = (TextView) itemView.findViewById(R.id.txt_section_name);
        }

        public void setSectionName(String sectionName) {
            txtSectionName.setText(sectionName);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView imgPortrait;
        private TextView txtItemName;
        private TextView txtItemEmail;
        private TextView txtItemPosition;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgPortrait = (CircleImageView) itemView.findViewById(R.id.img_group_member);
            txtItemName = (TextView) itemView.findViewById(R.id.txt_item_group_member_name);
            txtItemEmail = (TextView) itemView.findViewById(R.id.txt_group_member_email);
            txtItemPosition = (TextView) itemView.findViewById(R.id.txt_group_member_leader);
        }

        public void setItemPortrait(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .into(imgPortrait);
        }

        public void setItemName(String memberName) {
            Log.d("onBindViewHolder", "setItemName: " + memberName);
            txtItemName.setText(memberName);
        }

        public void setItemEmail(String memberEmail) {
            txtItemEmail.setText(memberEmail);
            Log.d("onBindViewHolder", "setItemEmail: " + memberEmail);
        }

        public void setItemPosition(String position) {
            // 若成員的職位為組員的話就把組長的TextView隱藏
            if(position.equals("組員")) {
                txtItemPosition.setVisibility(View.GONE);
            }
        }
    }
}
