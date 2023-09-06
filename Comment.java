package com.campudual.RedSocial;

import java.time.LocalDateTime;
import java.util.Date;

public class Comment {
    protected String text;
    protected LocalDateTime creationDate;
    protected User author;

    public Comment(String text,User author) {
        this.text = text;
        this.author = author;
        this.creationDate = LocalDateTime.now();
    }


    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return creationDate;
    }

    public User getAuthor() {
        return author;
    }
}