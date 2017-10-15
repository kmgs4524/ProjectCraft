package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import android.provider.Telephony;

import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/15.
 */

public class SaveGroupMember {
    // 存放被選擇成員的list
    private ArrayList<Member> memList;

    public SaveGroupMember() {
        this.memList = new ArrayList<>();
    }

    //
    public void addMember(Member member) {
        memList.add(member);
    };

    public ArrayList<Member> getMemList() {
        return memList;
    }

    public void setMemList(ArrayList<Member> memList) {
        this.memList = memList;
    }
}
