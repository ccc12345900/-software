package com.example.software.Dao;

import com.example.software.Model.book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Mapper
public interface ItemDao{
    int insertBook(book book);
    List<book> findBooks(String title);
    book findBook(String title);
}
