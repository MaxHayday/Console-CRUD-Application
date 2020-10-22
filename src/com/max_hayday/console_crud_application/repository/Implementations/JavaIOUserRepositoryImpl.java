package com.max_hayday.console_crud_application.repository.Implementations;

import com.max_hayday.console_crud_application.controller.PostController;
import com.max_hayday.console_crud_application.controller.RegionController;
import com.max_hayday.console_crud_application.model.Post;
import com.max_hayday.console_crud_application.model.Region;
import com.max_hayday.console_crud_application.model.Role;
import com.max_hayday.console_crud_application.model.User;
import com.max_hayday.console_crud_application.repository.UserRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class JavaIOUserRepositoryImpl implements UserRepository {
    private static final Path userPath = Paths.get("//home/max/IdeaProjects/ConsoleCRUDApplication/src/resources/user.txt");
    private static Long countId = 1L;

    private PostController postController;
    private Post post;
    private List<Post> postList;
    private RegionController regionController;
    private List<User> userList;
    private List<String> list;
    private BufferedReader reader;
    private BufferedWriter writer;


    public JavaIOUserRepositoryImpl() throws IOException, ParseException {
        postController = new PostController();
        regionController = new RegionController();
        userList = getAll();
        for (User u :
                userList) {
            if (u.getId() > countId) {
                countId = u.getId();
            } else countId = 1L;
        }

    }

    @Override
    public User getById(Long id) throws IOException, ParseException {
        reader = Files.newBufferedReader(userPath);
        list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) == id) {
                String[] strings = s.split("\\s+|\\.\\s*");
                Region region = regionController.getById(id);
                Post post = postController.getById(id);
                List<Post> posts = new ArrayList<>();
                posts.add(post);
                Role role = Role.valueOf(s.split("\\s+|\\.\\s*")[3]);
                return new User(id, strings[1], strings[2], posts, region, role);
            }
        }
        return null;
    }

    @Override
    public User save(User user) throws IOException {
        String regionStr = (++countId) + "." + user.getFirstName() + " " + user.getLastName() + " " + user.getRole() + "\n";
        Files.write(userPath, regionStr.getBytes(), StandardOpenOption.APPEND);
        regionController.save(user.getRegion().getName());
        String post = "";
        for (Post p :
                user.getPosts()) {
            post = p.getContent() + post;
        }
        postController.save(post);
        return user;
    }

    @Override
    public User update(User user) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) == user.getId()) {
                writer.write(user.getId() + "." + user.getFirstName() + " " + user.getLastName() + " " + user.getRole());
            } else {
                writer.write(s);
            }
            writer.newLine();
        }

        writer.close();
        regionController.update(user.getRegion());
        postController.update(user.getPosts());
        return null;
    }

    @Override
    public User update(List<User> t) {
        return null;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException {
        userList = new ArrayList();
        Long id;
        String firstName, lastName;
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            String[] str = reader.readLine().split("\\s+|\\.\\s*");
            if (str.length != 0 && !(str[0].isEmpty())) {
                id = Long.parseLong(str[0]);
                firstName = str[1];
                lastName = str[2];
                Role role = Role.valueOf(str[3]);
                Region region = regionController.getById(id);
                post = postController.getById(id);
                String[] posts = post.getContent().split("\\s+|,\\s*");
                postList = new ArrayList();
                for (String s : posts) {
                    postList.add(new Post(id, s, null, null));
                }
                userList.add(new User(id, firstName, lastName, postList, region, role));
            }
        }
        reader.close();
        return userList;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) != id) {
                writer.write(s);
                writer.newLine();
            }
        }
        writer.close();
        postController.deleteById(id);
        regionController.deleteById(id);
        --countId;
    }
}
