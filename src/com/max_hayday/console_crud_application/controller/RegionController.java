package com.max_hayday.console_crud_application.controller;

import com.max_hayday.console_crud_application.model.Region;
import com.max_hayday.console_crud_application.repository.JavaIORegionRepositoryImpl;
import com.max_hayday.console_crud_application.repository.RegionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegionController {
    Region region;
    List<Region> list = new ArrayList<>();
    RegionRepository regionRepository;

    public RegionController() throws IOException {
        regionRepository = new JavaIORegionRepositoryImpl();
    }

    public void save(String name) throws IOException {
        region = new Region(0L, name);
        region = regionRepository.save(region);
        if (region != null) {
            list.add(region);
        }
    }

    public void update(Long id, String updatedData) throws IOException {
        regionRepository.update(new Region(id, updatedData));
    }

    public List<Region> getAll() throws IOException {
        return regionRepository.getAll();
    }

    public void deleteById(Long id) throws IOException {
        regionRepository.deleteById(id);
    }

    public Region getById(Long id) throws IOException {
        return regionRepository.getById(id);
    }


}
