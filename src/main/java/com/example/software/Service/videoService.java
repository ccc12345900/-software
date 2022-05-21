package com.example.software.Service;

import com.example.software.Dao.videoDao;
import com.example.software.Model.video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class videoService {
    @Autowired
    private videoDao videoDao;

    public int inserVideService(video video){return videoDao.inserVide(video);};

    public List<video> findallService(){return videoDao.findall();};

    public int removeallService(){return videoDao.removeall();};
}
