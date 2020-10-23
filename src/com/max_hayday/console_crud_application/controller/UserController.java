package com.max_hayday.console_crud_application.controller;

import com.max_hayday.console_crud_application.model.Post;
import com.max_hayday.console_crud_application.model.Region;
import com.max_hayday.console_crud_application.model.Role;
import com.max_hayday.console_crud_application.model.User;
import com.max_hayday.console_crud_application.repository.implementations.JavaIOUserRepositoryImpl;
import com.max_hayday.console_crud_application.repository.UserRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserController {
    private UserRepository repository;

    public UserController() throws IOException, ParseException {
        repository = new JavaIOUserRepositoryImpl();
    }

    public void save(Long id, String firstName, String lastName, List<Post> posts, Region region, Role data) throws IOException {
        repository.save(new User(id, firstName, lastName, posts, region, data));
    }

    public List<User> getAll() throws IOException, ParseException {
        return repository.getAll();
    }

    public void update(User user) throws IOException {
        repository.update(user);
    }

    public void deleteById(Long id) throws IOException {
        repository.deleteById(id);
    }

    public User getById(Long id) throws IOException, ParseException {
        return repository.getById(id);
    }
}
