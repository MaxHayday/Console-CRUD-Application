package com.max_hayday.console_crud_application.controller;

import com.max_hayday.console_crud_application.model.Region;
import com.max_hayday.console_crud_application.repository.implementations.JavaIORegionRepositoryImpl;
import com.max_hayday.console_crud_application.repository.RegionRepository;

import java.io.IOException;
import java.util.List;

public class RegionController {
    private RegionRepository regionRepository;

    public RegionController() throws IOException {
        regionRepository = new JavaIORegionRepositoryImpl();
    }

    public void save(String name) throws IOException {
        regionRepository.save(new Region(null, name));
    }

    public void update(Region r) throws IOException {
        regionRepository.update(r);
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
