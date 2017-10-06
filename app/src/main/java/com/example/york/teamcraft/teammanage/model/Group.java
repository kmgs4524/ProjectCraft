package com.example.york.teamcraft.teammanage.model;

/**
 * Created by York on 2017/10/7.
 */

public class Group {
    private String name;
    private String objective;
    private String memNum;

    public Group(String name, String objective) {
        this.name = name;
        this.memNum = objective;
    }

    public String getName() {
        return name;
    }

    public String getObjective() {
        return objective;
    }

    public String getMemNum() {
        return memNum;
    }
}
