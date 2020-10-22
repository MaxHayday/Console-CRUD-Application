package com.max_hayday.console_crud_application.repository;

import com.max_hayday.console_crud_application.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface UserRepository extends GenericRepository<User,Long>{

    User getById(Long id) throws IOException, ParseException;

    User save(User user) throws IOException;

    User update(User user) throws IOException;

    List<User> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
