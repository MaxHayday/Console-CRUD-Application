package com.max_hayday.console_crud_application.view;

import com.max_hayday.console_crud_application.controller.RegionController;
import com.max_hayday.console_crud_application.model.Region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class RegionView {
    RegionController controller = new RegionController();
    String data;
    List<Region> regionsList;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Long id = 0L;

    public RegionView() throws IOException {
    }

    public void showMenu() throws IOException {
        System.out.println("************************************************** W E L K O M **************************************");
        reader = new BufferedReader(new InputStreamReader(System.in));


        do {
            System.out.println("\n--------------------------------------- Choose one of options ---------------------------------------");
            System.out.println("1: Create region;");
            System.out.println("2: Show regions;");
            System.out.println("3: Update region;");
            System.out.println("4: Delete by Id;");
            System.out.println("5: Get by Id;");

            System.out.println("For exit write exit");
            data = reader.readLine();
            if (!data.matches("[0-9]")) {
                System.out.println("You need to write number.");
            }
            if (data.isEmpty()) {
                System.out.println("Please choose correct number.");
            }
            switch (data) {
                case "1" -> save();
                case "2" -> getAll();
                case "3" -> update();
                case "4" -> delete();
                case "5" -> getUserById();
            }
        } while (!data.equals("exit"));
    }

    public void save() {
        System.out.println("Write name of region: ");
        try {
            data = reader.readLine();
            if (!(data.matches("[0-9]") || data.isEmpty() || data.equals(" "))) {
                controller.save(data);

            } else System.out.println("You need to write a name.");

        } catch (IOException e) {
            System.out.println("Please wright correct name;");
        }
    }

    public void getAll() {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) {
                System.out.println("YOU HAVE NOT REGIONS.");
                return;
            }
            System.out.println("=============");
            System.out.println("ID       NAME");
            System.out.println("=============");
            for (Region i :
                    regionsList) {
                System.out.printf("%-9s", i.getId() + ".");
                System.out.println(i.getName());
            }
        } catch (IOException e) {
            System.out.println("You have not regions.");
        }
    }

    public void update() {
        System.out.println("Write id of region which do you want to change: ");
        try {
            id = Long.parseLong(reader.readLine());
            System.out.println("Write new name of region: ");
            data = reader.readLine();
            controller.update(id, data);
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void delete() {
        System.out.println("Write id of region which do you want to delete: ");
        try {
            id = Long.parseLong(reader.readLine());
            controller.deleteById(id);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    public void getUserById() {
        System.out.println("Write id of region which do you want to delete: ");
        try {
            id = Long.parseLong(reader.readLine());
            Region region = controller.getById(id);
            System.out.println(region.getId() + "." + region.getName());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
