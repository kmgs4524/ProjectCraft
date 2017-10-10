package com.example.york.teamcraft.teammanage.model;

/**
 * Created by York on 2017/10/7.
 */

public class Group {
    private String name;
    private String id;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {}

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}
