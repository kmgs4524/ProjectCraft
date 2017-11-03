package com.example.york.teamcraft.teammanage.post.model;

/**
 * Created by York on 2017/10/20.
 */

public class Comment {
    private String authorName;
    private String authorId;
    private String message;
    private String commentId;
    private String imageUrl;

    public Comment(String authorName, String authorId, String message, String commentId) {
        this.authorName = authorName;
        this.authorId = authorId;
        this.message = message;
        this.commentId = commentId;
    }

    public Comment() {}

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
