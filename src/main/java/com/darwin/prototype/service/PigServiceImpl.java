package com.darwin.prototype.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class PigServiceImpl implements PigService {

    @Override
    public void create() {
        System.out.println("create");
    }

    @Override
    public void delete(long ID) {
        System.out.println("delete");
    }

    @Override
    public void getByID(Long ID) {
        System.out.println("getByID");
    }

    @Override
    public void update(long[] IDs) {
        System.out.println("update");
    }

    @Override
    public void deleteAll() {
        System.out.println("deleteAll");
    }

    @Override
    public void getAll() {
        System.out.println("getAll");
    }
}
