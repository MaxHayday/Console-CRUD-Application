package com.max_hayday.console_crud_application.repository.implementations;


import com.max_hayday.console_crud_application.model.Region;
import com.max_hayday.console_crud_application.repository.RegionRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class JavaIORegionRepositoryImpl implements RegionRepository {

    private static final Path regionPath = Paths.get("//home/max/IdeaProjects/ConsoleCRUDApplication/src/resources/region.txt");
    private static Long countId = 0L;
    private List<Region> regionList;
    private List<String> list;
    private BufferedReader reader;
    private BufferedWriter writer;


    public JavaIORegionRepositoryImpl() throws IOException {
        regionList = getAll();
        for (Region region :
                regionList) {
            if (region.getId() > countId) {
                countId = region.getId();
            } else countId = 0L;
        }
    }

    @Override
    public Region save(Region r) throws IOException {
        String regionStr = (++countId) + "." + r.getName() + "/" + "\n";
        Files.write(regionPath, regionStr.getBytes(), StandardOpenOption.APPEND);
        return r;
    }

    @Override
    public Region getById(Long id) throws IOException {
        reader = Files.newBufferedReader(regionPath);
        list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String s :
                list) {
            if (!(s.isEmpty())) {
                if (Long.parseLong(s.split("\\.")[0]) == id) {
                    return new Region(id, s.split("\\.")[1]);
                }
            } else break;
        }
        return null;
    }

    @Override
    public Region update(Region r) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(regionPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(regionPath);
        for (String s : list) {
            if (Long.parseLong(s.split("\\.")[0]) == r.getId()) {
                writer.write(r.getId() + "." + r.getName() + "/");
            } else {
                writer.write(s);
            }
            writer.newLine();
        }
        writer.close();
        return null;
    }

    @Override
    public Region update(List<Region> t) throws IOException {
        return null;
    }

    @Override
    public List<Region> getAll() throws IOException {
        regionList = new ArrayList();
        reader = Files.newBufferedReader(regionPath);
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.length() != 0) {
                String[] ar = line.split("\\.");
                Long id = Long.parseLong(ar[0]);
                String name = ar[1];
                regionList.add(new Region(id, name));
            }
        }
        reader.close();
        return regionList;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(regionPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(regionPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) != id) {
                writer.write(s);
                writer.newLine();
            }
        }
        writer.close();
        --countId;
    }
}
