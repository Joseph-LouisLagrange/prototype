package com.darwin.prototype.service;


import org.springframework.security.access.prepost.PreAuthorize;


public interface PigService {
    @PreAuthorize("hasPermission('*','Pig','create')")
    public void create();

    @PreAuthorize("hasPermission(ID,'Pig','delete')")
    public void delete(long ID);

    @PreAuthorize("hasPermission(ID,'Pig','get')")
    public void getByID(long ID);

    @PreAuthorize("hasPermission(ID,'Pig','update')")
    public void update(long ID);

    @PreAuthorize("hasPermission('*','Pig','delete')")
    public void deleteAll();

    @PreAuthorize("hasPermission('*','Pig','get')")
    public void getAll();
}
