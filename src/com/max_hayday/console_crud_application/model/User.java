package com.max_hayday.console_crud_application.model;

import java.util.List;

public class User extends AbstractBaseEntity {

    private List<Post> posts;
    private Region region;
    private Role role;

    public User(long id, String firstName, String lastName, List<Post> posts, Region region, Role role) {
        super(id, firstName, lastName);
        this.posts = posts;
        this.region = region;
        this.role = role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Role getRole() {
        return role;
    }

}
