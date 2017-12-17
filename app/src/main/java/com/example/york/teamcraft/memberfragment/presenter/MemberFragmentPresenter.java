package com.example.york.teamcraft.memberfragment.presenter;

import com.example.york.teamcraft.CallBack;

/**
 * Created by York on 2017/12/4.
 */

public interface MemberFragmentPresenter {
    public abstract void checkState(CallBack<Boolean> callBack);
    public abstract void initRecyclerViewData();
    public abstract void initSearchId();
}
