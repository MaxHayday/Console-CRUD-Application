package com.max_hayday.console_crud_application.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Menu {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private UserView userView;
    private PostView postView;
    private RegionView regionView;
    private String data;
    private Long id = 0L;
    private boolean isTrue = false;

    public Menu() throws IOException, ParseException {
        userView = new UserView();
        postView = new PostView();
        regionView = new RegionView();
    }

    public void showMenu() throws IOException {
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
                System.out.println("You need to write a correct number.");
            }
            switch (data) {
                case "1":
                    isTrue = userView.save();
                    if (isTrue) {
                        regionView.save();
                        postView.save();
                    }
                    break;
                case "2":
                    userView.getAll();
                    regionView.getAll();
                    postView.getAll();
                    break;
                case "3":
                    System.out.println("Write id of user which do you want to change: ");
                    try {
                        data = reader.readLine();
                        if (!(data.isEmpty() || data.matches("[^\\w]"))) {
                            id = Long.parseLong(data);
                            isTrue = userView.update(id);
                            if (isTrue) {
                                regionView.update(id);
                                postView.update(id);
                            }
                        } else {
                            System.out.println("You need to write number.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                    }
                    break;
                case "4":
                    System.out.println("Write id of user which do you want to delete: ");
                    try {
                        id = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    userView.delete(id);
                    regionView.delete(id);
                    postView.delete(id);
                    break;
                case "5":
                    System.out.println("Write id of user: ");
                    try {
                        id = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    userView.getUserById(id);
                    regionView.getUserById(id);
                    postView.getPostById(id);
                    break;
            }
        } while (!data.equals("exit"));
    }
}
