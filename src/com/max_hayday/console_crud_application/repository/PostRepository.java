package com.max_hayday.console_crud_application.repository;

import com.max_hayday.console_crud_application.model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    Post getById(Long id) throws IOException, ParseException;

    Post save(Post post) throws IOException;

    Post update(List<Post> posts) throws IOException;

    List<Post> getAll() throws IOException;

    void deleteById(Long id) throws IOException;


}
