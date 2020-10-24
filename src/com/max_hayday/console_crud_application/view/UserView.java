package com.max_hayday.console_crud_application.view;

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

    boolean save() {
        postList = new ArrayList<>();
        try {
            System.out.println("Write first name of user: ");
            firstNameData = reader.readLine();
            if (checkForCorrectInputWord(firstNameData)) {
                System.out.println("Write last name of user: ");
                lastNameData = reader.readLine();
                if (checkForCorrectInputWord(lastNameData)) {
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
                        default:
                            System.out.println("You wrote wrong number");
                            return false;
                    }
                } else return false;
            } else return false;
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again.");
        }
        return true;
    }

    void getUserById(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
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

    boolean update(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return false;
            }
            if (userList.size() < id || id <= 0) {
                System.out.println("You have not user with ID: " + id);
                return false;
            }
            System.out.println("Write first name of user: ");
            firstNameData = reader.readLine();
            if (checkForCorrectInputWord(firstNameData)) {
                System.out.println("Write last name of user: ");
                lastNameData = reader.readLine();
                if (checkForCorrectInputWord(lastNameData)) {
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
                        default:
                            System.out.println("You wrote wrong number");
                            return false;
                    }
                } else return false;
            } else return false;
        } catch (IOException | ParseException exception) {
            System.out.println("Something went wrong. Please try again.");
        }
        return true;
    }

    void delete(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            if (userList.size() < id || id <= 0) {
                System.out.println("You have not user with ID: " + id);
                return;
            }
            userController.deleteById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Write correct id.");
        }
    }

    private boolean checkForCorrectInputWord(String s) {
        if (!s.isEmpty() && !s.matches("[0-9]")) {
            return true;
        } else {
            System.out.println("You wrote incorrect word.");
            return false;
        }
    }
}
