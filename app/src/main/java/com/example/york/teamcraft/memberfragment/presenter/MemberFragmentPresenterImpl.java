package com.example.york.teamcraft.memberfragment.presenter;

import com.example.york.teamcraft.memberfragment.view.MemberView;
import com.example.york.teamcraft.memberfragment.viewmodel.GetMemberData;

/**
 * Created by York on 2017/12/4.
 */

public class MemberFragmentPresenterImpl implements MemberFragmentPresenter{
    private MemberView memberView;

    public MemberFragmentPresenterImpl(MemberView view) {
        this.memberView = view;
    }

    @Override
    public void initRecyclerViewData() {
        GetMemberData getMemberData = new GetMemberData(memberView);
        getMemberData.getData();
    }
}
