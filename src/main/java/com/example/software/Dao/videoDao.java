package com.example.software.Dao;

import com.example.software.Model.video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface videoDao {
    public int inserVide(video video);
    public List<video> findall();
    public int removeall();
}
