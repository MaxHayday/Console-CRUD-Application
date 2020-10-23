package com.max_hayday.console_crud_application.view;

import com.max_hayday.console_crud_application.controller.PostController;
import com.max_hayday.console_crud_application.model.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PostView {
    PostController controller = new PostController();
    List<Post> postList;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String data;

    public PostView() throws IOException {
    }

    public void save() {
        System.out.println("Write name of post: ");
        try {
            data = reader.readLine();
            if (!(data.isEmpty() || data.matches("[0-9]") || data.matches("[^\\w]")))  {
                controller.save(data);
            } else return;
        } catch (IOException e) {
            System.out.println("Please wright correct name;");
        }
    }

    public void getAll() {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            for (Post i :
                    postList) {
                System.out.printf("%-25s%n", i.getContent());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("You have not posts.");
        }

    }

    public void update(Long id) {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            System.out.println("Write new name of post: ");
            postList = new ArrayList<>();
            postList.add(new Post(id, reader.readLine(), null, null));
            controller.update(postList);
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void delete(Long id) {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            controller.deleteById(id);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    public void getPostById(Long id) {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            Post post = controller.getById(id);
            System.out.printf("%-20s%-25s%-20s%n", post.getContent(), post.getCreated(), post.getUpdated());
        } catch (IOException | NumberFormatException | ParseException e) {
            System.out.println("Write correct id.");
        }
    }
}
