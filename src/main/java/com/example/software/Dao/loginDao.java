package com.example.software.Dao;

import com.example.software.Model.loginModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface loginDao {
    loginModel userfind(String username,String password);
}
