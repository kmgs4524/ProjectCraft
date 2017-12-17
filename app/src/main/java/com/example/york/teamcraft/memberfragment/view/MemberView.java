package com.example.york.teamcraft.memberfragment.view;

import com.example.york.teamcraft.memberfragment.data.SectionOrItem;

import java.util.ArrayList;

/**
 * Created by York on 2017/12/4.
 */

public interface MemberView {
    public abstract void initTxtSearchId(String searchId);
    public abstract void initRecyclerView(ArrayList<SectionOrItem> sectionOrItems);
}
