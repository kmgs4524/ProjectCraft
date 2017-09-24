package com.example.york.teamcraft;

/**
 * Created by York on 2017/9/23.
 */

public interface CallBack<E> {
    public abstract void update(E e, String key);
}
