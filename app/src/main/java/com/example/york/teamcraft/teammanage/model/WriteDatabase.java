package com.example.york.teamcraft.teammanage.model;

import java.util.Map;

/**
 * Created by York on 2017/9/24.
 */

public interface WriteDatabase<K, V> {
    public abstract void updateData();
    public abstract void pushData(Map<K, V> map);
}
