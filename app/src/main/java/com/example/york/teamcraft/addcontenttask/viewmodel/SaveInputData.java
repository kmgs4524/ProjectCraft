package com.example.york.teamcraft.addcontenttask.viewmodel;

import com.example.york.teamcraft.addcontenttask.data.Date;
import com.example.york.teamcraft.member.Member;

/**
 * Created by York on 2017/10/17.
 */

public class SaveInputData {
    private String date;
    private Member member;

    public void saveDate(int y, int m, int d) {
        date = y + "/" + m + "/" + d;
    }

    public String getDate() {
        return date;
    }

    public void saveResponsible(Member member) {
        this.member = member;
    }

    public Member getResponsible() {
        return member;
    }

}
