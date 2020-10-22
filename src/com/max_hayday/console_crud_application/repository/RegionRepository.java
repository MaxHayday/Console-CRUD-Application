package com.max_hayday.console_crud_application.repository;

import com.max_hayday.console_crud_application.model.Region;

import java.io.IOException;
import java.util.List;

public interface RegionRepository extends GenericRepository<Region, Long> {

    Region getById(Long id) throws IOException;

    Region save(Region o) throws IOException;

    Region update(Region o) throws IOException;

    List<Region> getAll() throws IOException;

    void deleteById(Long id) throws IOException;

}
