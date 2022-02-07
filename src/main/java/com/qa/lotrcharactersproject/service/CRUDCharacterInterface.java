package com.qa.lotrcharactersproject.service;

import java.util.List;

public interface CRUDCharacterInterface<T> {

    //CRUD Interface

    //Create
    T create(T t);

    //Read all
    List<T> readAll();

    //Read by ID
    T readById(Long id);

    //Update
    T update(T t, Long id);

    //Delete
    boolean delete(Long id);
}
