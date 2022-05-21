package com.example.software.Service.impl;


import com.example.software.Dao.ItemDao;
import com.example.software.Model.book;
import com.example.software.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Override
    @Transactional
    public void Save(book item) {
        this.itemDao.insertBook(item);
    }

    @Override
    public List<book> findAll(String item) {
        //声明查询条件进行查询数据
        List<book> list = this.itemDao.findBooks(item);
        return list;
    }
}
