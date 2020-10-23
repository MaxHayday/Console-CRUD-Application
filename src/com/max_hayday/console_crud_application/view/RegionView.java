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
    Region region;

    public RegionView() throws IOException {
    }

    public void save() {
        try {
            System.out.println("Write a region: ");
            data = reader.readLine();
            if (!(data.isEmpty() || data.matches("[0-9]") || data.matches("[^\\w]")))  {
                controller.save(data);
            } else return;
        } catch (IOException e) {
            System.out.println("Please wright correct region;");
        }
    }

    public void getAll() {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) {
                return;
            }
            for (Region i :
                    regionsList) {
                System.out.printf("%-25s", i.getName());
            }
        } catch (IOException e) {
            System.out.println("You have not regions.");
        }
    }

    public void update(Long id) {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) return;
            System.out.println("Write new region: ");
            data = reader.readLine();
            controller.update(new Region(id, data));
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    public void delete(Long id) {
        try {
            controller.deleteById(id);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    public void getUserById(Long id) {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) return;
            region = controller.getById(id);
            System.out.printf("%-20s", region.getName());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
