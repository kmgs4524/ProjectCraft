package com.example.york.teamcraft;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/23.
 */

public interface CallBack<D> {
//    public abstract void update(ArrayList<Work> list);
    public abstract void update(D data);
}
