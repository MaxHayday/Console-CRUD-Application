package com.max_hayday.console_crud_application.controller;

import com.max_hayday.console_crud_application.model.Post;
import com.max_hayday.console_crud_application.repository.Implementations.JavaIOPostRepositoryImpl;
import com.max_hayday.console_crud_application.repository.PostRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class PostController {
    private PostRepository postRepository;

    public PostController() throws IOException {
        postRepository = new JavaIOPostRepositoryImpl();
    }

    public void save(String content) throws IOException {
        postRepository.save(new Post(0L, content, null, null));
    }

    public void update(List<Post> lis) throws IOException {
        postRepository.update(lis);
    }

    public List<Post> getAll() throws IOException {
        return postRepository.getAll();
    }

    public void deleteById(Long id) throws IOException {
        postRepository.deleteById(id);
    }

    public Post getById(Long id) throws IOException, ParseException {
        return postRepository.getById(id);
    }
}
