package com.example.software.Service;

import com.example.software.Dao.loginDao;
import com.example.software.Model.loginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginService {
    @Autowired
    private loginDao loginDao;

    public loginModel userfindService(String username,String password){return loginDao.userfind(username,password);}
}
