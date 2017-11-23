package com.example.york.teamcraft.teammanage.creategroup.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by York on 2017/10/14.
 */

public class SpinMenuAdapter extends ArrayAdapter<GroupMember> {
    private FragmentActivity fragmentActivity;
    private ArrayList<GroupMember> itemList = new ArrayList<>();

    public SpinMenuAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<GroupMember> list) {
        super(context, resource);
        this.fragmentActivity = (FragmentActivity) context;
        this.itemList = list;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public GroupMember getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = fragmentActivity.getLayoutInflater();
        if(position == 0) {
            View view = new View(getContext());
            view.setMinimumHeight(0);
            return view;
        } else {
            View view = inflater.inflate(R.layout.spinner_item_layout, parent, false);
            TextView txtName = (TextView) view.findViewById(R.id.txt_spin_team_member_name);
            txtName.setText(itemList.get(position).getName());
            CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.cir_img_spin_team_member);
            Picasso.with(getContext())
                    .load(itemList.get(position).getPhotoUrl())
                    .into(circleImageView);
            return view;
        }
    }

}
