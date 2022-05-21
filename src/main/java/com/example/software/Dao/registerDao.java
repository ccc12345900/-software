package com.example.software.Dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface registerDao {
    int Registerdao(String username,String password);
}
