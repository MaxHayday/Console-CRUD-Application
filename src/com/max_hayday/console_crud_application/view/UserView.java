package com.max_hayday.console_crud_application.view;

import com.max_hayday.console_crud_application.controller.PostController;
import com.max_hayday.console_crud_application.controller.RegionController;
import com.max_hayday.console_crud_application.controller.UserController;
import com.max_hayday.console_crud_application.model.Post;
import com.max_hayday.console_crud_application.model.Role;
import com.max_hayday.console_crud_application.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserView {
    private UserController userController;
    private List<User> userList;
    private List<Post> postList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Long id = 0L;
    private String firstNameData = "", lastNameData = "", data;
    private User user;


    public UserView() throws IOException, ParseException {
        userController = new UserController();
    }

    void save() {
        postList = new ArrayList<>();
        System.out.println("Write first name of user: ");
        try {
            data = reader.readLine();
            if (!(data.isEmpty() || data.matches("[0-9]") || data.matches("[^\\w]"))) {
                firstNameData = data;
            } else {
                System.out.println("You wrote wrong first name please try again.");
                return;
            }
            System.out.println("Write last name of user: ");
            data = reader.readLine();
            if (!(data.isEmpty() || data.equals(" "))) {
                lastNameData = data;
            } else System.out.println("You need to write a last name.");
            System.out.println("Choose role of user: ");
            System.out.println("1: User");
            System.out.println("2: Admin");
            System.out.println("3: Moderator");
            data = reader.readLine();
            switch (data) {
                case "1":
                    userController.save(id, firstNameData, lastNameData, postList, null, Role.USER);
                    break;
                case "2":
                    userController.save(id, firstNameData, lastNameData, postList, null, Role.ADMIN);
                    break;
                case "3":
                    userController.save(id, firstNameData, lastNameData, postList, null, Role.MODERATOR);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again.");
        }

    }

    void getUserById(Long id) {
        try {
            user = userController.getById(id);
            if (user == null) {
                System.out.println("YOU HAVE NOT USER WITH ID: " + id);
                return;
            }
            System.out.println("================================================================================================================================================");
            System.out.printf("%-5s%-15s%-20s%-20s%-20s%-20s%-25s%-20s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS", "POST CREATED", "POST UPDATED");
            System.out.println("================================================================================================================================================");
            System.out.printf("%-5s%-15s%-20s%-20s", user.getId(), user.getFirstName(), user.getLastName(), user.getRole());
        } catch (IOException | ParseException e) {
            System.out.println("You have not users.");
        }
    }

    void getAll() {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            System.out.println("======================================================================================================================");
            System.out.printf("%-5s%-15s%-25s%-25s%-25s%-25s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS");
            System.out.println("======================================================================================================================");
            for (User i :
                    userList) {
                System.out.printf("%-5s%-15s%-25s%-25s", i.getId(), i.getFirstName(), i.getLastName(), i.getRole());
            }
        } catch (IOException | ParseException e) {
            System.out.println("You have not users.");
        }
    }

    void update(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            System.out.println("Write first name of user: ");
            firstNameData = reader.readLine();
            System.out.println("Write last name of user: ");
            lastNameData = reader.readLine();
            System.out.println("Choose role of user: ");
            System.out.println("1: User");
            System.out.println("2: Admin");
            System.out.println("3: Moderator");
            data = reader.readLine();
            switch (data) {
                case "1":
                    userController.update(new User(id, firstNameData, lastNameData, postList, null, Role.USER));
                    break;
                case "2":
                    userController.update(new User(id, firstNameData, lastNameData, postList, null, Role.ADMIN));
                    break;
                case "3":
                    userController.update(new User(id, firstNameData, lastNameData, postList, null, Role.MODERATOR));
                    break;
            }
        } catch (IOException | ParseException exception) {
            System.out.println("Something went wrong. Please try again.");
        }
    }

    void delete(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            userController.deleteById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Write correct id.");
        }
    }
}
