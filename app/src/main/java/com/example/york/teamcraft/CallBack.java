package com.example.york.teamcraft;

import android.support.v7.widget.RecyclerView;

import com.example.york.teamcraft.teammanage.BoardItemAdapter;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/23.
 */

public interface CallBack {
    public abstract void update(ArrayList<Activity> list);
}
