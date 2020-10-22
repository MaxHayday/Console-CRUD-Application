package com.max_hayday.console_crud_application.model;


public class Post {
    private Long id;
    private String content;
    private String created;
    private String updated;

    public Post(Long id, String content, String created, String updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}


