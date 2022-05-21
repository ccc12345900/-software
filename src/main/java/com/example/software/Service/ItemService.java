package com.example.software.Service;


import com.example.software.Model.book;

import java.util.List;


public interface ItemService {
    public void Save(book item);

    public List<book> findAll(String item);
}
