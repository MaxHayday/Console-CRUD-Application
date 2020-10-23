package com.max_hayday.console_crud_application.view;

import com.max_hayday.console_crud_application.controller.PostController;
import com.max_hayday.console_crud_application.controller.RegionController;
import com.max_hayday.console_crud_application.controller.UserController;
import com.max_hayday.console_crud_application.model.Post;
import com.max_hayday.console_crud_application.model.Region;
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
    private PostController postController;
    private RegionController regionController;
    private List<User> userList;
    private List<Post> postList;
    private List<Region> regionList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Long id = 0L;
    private String firstNameData = "", lastNameData = "", data, regionStr = "", postStr = "";
    private Region region;
    private User user;
    private Post post;


    public UserView() throws IOException, ParseException {
        userController = new UserController();
        postController = new PostController();
        regionController = new RegionController();
    }

    public void showUserView() throws IOException {
        do {
            System.out.println("\n----------------------------------------------- Choose one of options -----------------------------------------------");
            System.out.println("1: Create user;");
            System.out.println("2: Show users;");
            System.out.println("3: Update user;");
            System.out.println("4: Delete user by Id;");
            System.out.println("5: Get user by Id;");

            System.out.println("For exit write exit");
            data = reader.readLine();
            if (!data.matches("[0-5]") && !data.equals("exit")) {
                System.out.println("You need to write number.");
            }
            switch (data) {
                case "1":
                    save();
                    break;
                case "2":
                    getAll();
                    break;
                case "3":
                    update();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    getUserById();
                    break;
            }
        } while (!data.equals("exit"));
    }

    private void getUserById() {
        System.out.println("Write id of user: ");
        try {
            id = Long.parseLong(reader.readLine());
            user = userController.getById(id);
            post = postController.getById(id);
            region = regionController.getById(id);
            if (user == null) {
                System.out.println("YOU HAVE NOT USER WITH ID: " + id);
                return;
            }
            System.out.println("================================================================================================================================================");
            System.out.printf("%-5s%-15s%-20s%-20s%-20s%-20s%-25s%-20s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS", "POST CREATED", "POST UPDATED");
            System.out.println("================================================================================================================================================");
            System.out.printf("%-5s%-15s%-20s%-20s%-20s", user.getId(), user.getFirstName(), user.getLastName(), user.getRole(), region.getName());
            System.out.printf("%-20s%-25s%-20s%n", post.getContent(), post.getCreated(), post.getUpdated());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (NumberFormatException | ParseException | IOException e) {
            System.out.println("You have not users.");
        }
    }

    private void save() {
        postList = new ArrayList<>();

        System.out.println("Write first name of user: ");
        try {
            data = reader.readLine();
            if (!(data.isEmpty() || data.equals(" "))) {
                firstNameData = data;
            } else
                System.out.println("You need to write a name.");
            System.out.println("Write last name of user: ");
            data = reader.readLine();
            if (!(data.isEmpty() || data.equals(" "))) {
                lastNameData = data;
            } else System.out.println("You need to write a last name.");
            System.out.println("Write region of user: ");
            data = reader.readLine();
            if (!(data.isEmpty() || data.equals(" "))) {
                regionStr = data;//= new Region(id, data)
            } else System.out.println("You need to write a region.");
            System.out.println("Write posts of user, separated by commas: ");
            data = reader.readLine();
            if (!(data.isEmpty() || data.equals(" "))) {
                postStr = data;
                postList.add(new Post(id, data, null, null));
            } else System.out.println("You need to write a posts.");
            System.out.println("Choose role of user: ");
            System.out.println("1: User");
            System.out.println("2: Admin");
            System.out.println("3: Moderator");
            data = reader.readLine();
            switch (data) {
                case "1":
                    userController.save(id, firstNameData, lastNameData, postList, region, Role.USER);
                    postController.save(postStr);
                    regionController.save(regionStr);
                    break;
                case "2":
                    userController.save(id, firstNameData, lastNameData, postList, region, Role.ADMIN);
                    postController.save(postStr);
                    regionController.save(regionStr);
                    break;
                case "3":
                    userController.save(id, firstNameData, lastNameData, postList, region, Role.MODERATOR);
                    postController.save(postStr);
                    regionController.save(regionStr);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again.");
        }

    }

    private void getAll() {
        try {
            userList = userController.getAll();
            postList = postController.getAll();
            regionList = regionController.getAll();
            if (userList.isEmpty()) {
                System.out.println("YOU HAVE NOT USER.");
                return;
            }
            System.out.println("======================================================================================================================");
            System.out.printf("%-5s%-15s%-25s%-25s%-25s%-25s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS");
            System.out.println("======================================================================================================================");
            for (User i :
                    userList) {
                System.out.printf("%-5s%-15s%-25s%-25s", i.getId(), i.getFirstName(), i.getLastName(), i.getRole());
                for (Region r :
                        regionList) {
                    if (r.getId() == i.getId()) {
                        System.out.printf("%-25s", r.getName());
                    }
                }
                for (Post p :
                        postList) {
                    if (p.getId() == i.getId()) {
                        System.out.print(p.getContent() + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
        } catch (NumberFormatException | ParseException | IOException e) {
            System.out.println("You have not users.");
        }
    }

    private void update() {
        System.out.println("Write id of user which do you want to change: ");
        try {
            id = Long.parseLong(reader.readLine());
            System.out.println("Write first name of user: ");
            firstNameData = reader.readLine();
            System.out.println("Write last name of user: ");
            lastNameData = reader.readLine();
            System.out.println("Write region of user: ");
            region = new Region(id, reader.readLine());
            System.out.println("Write posts of user, separated by commas: ");
            postList = new ArrayList<>();
            postList.add(new Post(id, reader.readLine(), null, null));
            System.out.println("Choose role of user: ");
            System.out.println("1: User");
            System.out.println("2: Admin");
            System.out.println("3: Moderator");
            data = reader.readLine();
            switch (data) {
                case "1":
                    userController.update(new User(id, firstNameData, lastNameData, postList, region, Role.USER));
                    regionController.update(region);
                    postController.update(postList);
                    break;
                case "2":
                    userController.update(new User(id, firstNameData, lastNameData, postList, region, Role.ADMIN));
                    regionController.update(region);
                    postController.update(postList);
                    break;
                case "3":
                    userController.update(new User(id, firstNameData, lastNameData, postList, region, Role.MODERATOR));
                    regionController.update(region);
                    postController.update(postList);
                    break;
            }
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Something went wrong. Please try again.");
        }
    }

    private void delete() {
        System.out.println("Write id of user which do you want to delete: ");
        try {
            id = Long.parseLong(reader.readLine());
            userController.deleteById(id);
            postController.deleteById(id);
            regionController.deleteById(id);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
