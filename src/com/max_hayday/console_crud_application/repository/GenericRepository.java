package com.max_hayday.console_crud_application.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(Long id) throws IOException;
    T save(T t) throws IOException;
    T update(T t) throws IOException;
    List<T> getAll() throws IOException;
    void deleteById(Long id) throws IOException;

}
