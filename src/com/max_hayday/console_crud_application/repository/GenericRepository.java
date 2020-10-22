package com.max_hayday.console_crud_application.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(Long id) throws IOException, ParseException;

    T save(T t) throws IOException;

    T update(T t) throws IOException;

    T update(List<T> t) throws IOException;

    List<T> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;

}
