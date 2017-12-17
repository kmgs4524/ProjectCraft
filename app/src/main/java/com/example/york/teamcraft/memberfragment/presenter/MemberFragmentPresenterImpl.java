package com.example.york.teamcraft.memberfragment.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.memberfragment.view.MemberView;
import com.example.york.teamcraft.memberfragment.viewmodel.CheckMemberExist;
import com.example.york.teamcraft.memberfragment.viewmodel.GetMemberData;
import com.example.york.teamcraft.memberfragment.viewmodel.GetSearchId;

/**
 * Created by York on 2017/12/4.
 */

public class MemberFragmentPresenterImpl implements MemberFragmentPresenter{
    private MemberView memberView;

    public MemberFragmentPresenterImpl(MemberView view) {
        this.memberView = view;
    }

    @Override
    public void checkState(final CallBack<Boolean> callBack) {
        CheckMemberExist checkMemberExist = new CheckMemberExist(memberView);
        checkMemberExist.checkMember(new CallBack<Boolean>() {
            @Override
            public void update(Boolean isExisting) {
                callBack.update(isExisting);
            }
        });
    }

    @Override
    public void initRecyclerViewData() {
        GetMemberData getMemberData = new GetMemberData(memberView);
        getMemberData.getData();
    }

    @Override
    public void initSearchId() {
        GetSearchId getSearchId = new GetSearchId();
        getSearchId.getSearchId(memberView);
    }
}
