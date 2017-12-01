package com.example.york.teamcraft.memberfragment.data;

/**
 * Created by York on 2017/12/1.
 */

public class SectionOrItem {
    String memberName;
    String groupName;
    String email;
    String imageUrl;
    String position;
    boolean isItem;

    public static SectionOrItem createSection(String groupName) {
        SectionOrItem sectionOrItem = new SectionOrItem();
        sectionOrItem.setGroupName(groupName);
        sectionOrItem.setIsItem(false);

        return sectionOrItem;
    }

    public static SectionOrItem createItem(String memberName, String email, String imageUrl, String position) {
        SectionOrItem sectionOrItem = new SectionOrItem();
        sectionOrItem.setMemberName(memberName);
        sectionOrItem.setEmail(email);
        sectionOrItem.setImageUrl(imageUrl);
        sectionOrItem.setPosition(position);
        sectionOrItem.setIsItem(true);

        return sectionOrItem;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isItem() {
        return isItem;
    }

    public void setIsItem(boolean item) {
        isItem = item;
    }
}
