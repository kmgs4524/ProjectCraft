package com.example.york.teamcraft.teammanage.creategroup.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.member.Member;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by York on 2017/10/14.
 */

public class SpinMenuAdapter extends ArrayAdapter<Member> {
    private FragmentActivity fragmentActivity;
    private ArrayList<Member> itemList = new ArrayList<>();

    public SpinMenuAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Member> list) {
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
    public Member getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = fragmentActivity.getLayoutInflater();
        View view = inflater.inflate(R.layout.spinner_item_layout, parent, false);

        TextView txtName = (TextView) view.findViewById(R.id.txt_spin_team_member_name);
        txtName.setText(itemList.get(position).getName());
        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.cir_img_spin_team_member);
//        circleImageView.setImageResource();

        return view;
    }

}