package com.max_hayday.console_crud_application.model;

public class AbstractBaseEntity {
    private Long id;
    private String firstName;
    private String lastName;

    public AbstractBaseEntity(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
