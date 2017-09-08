package com.example.york.teamcraft.personalsmanage;

/**
 * Created by York on 2017/9/7.
 */

class Note {
    private String title = null;
    private String content = null;
    private String date = null;
    private int importance = 0;

    public Note(String title, String content, String date, int importance) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.importance = importance;   // 分成三個急迫性等級，1: 急迫, 2: 適中, 3: 充裕
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
