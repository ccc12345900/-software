package com.example.software.Service;

import com.example.software.Dao.loginDao;
import com.example.software.Dao.registerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class registerService {
    @Autowired
    registerDao registerDao;

    public int registerservice(String username,String password){return registerDao.Registerdao(username,password);}
}
