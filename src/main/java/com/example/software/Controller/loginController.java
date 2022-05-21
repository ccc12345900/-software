package com.example.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.software.Model.loginModel;
import com.example.software.Service.loginService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @Autowired
    private loginService loginService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public String Login(String account, String password, HttpServletResponse response, HttpServletRequest request)
    {
        System.out.println(account+" "+password);
        JSONObject jsonObject = new JSONObject();
        loginModel loginModel = loginService.userfindService(account,password);
        System.out.println("创建时间" + loginModel.getCreateTime());
        if(loginModel != null) {
            Cookie cookie1 = new Cookie("username", loginModel.getUsername());
            response.addCookie(cookie1);
            HttpSession session = request.getSession();
            //第二步：将想要保存到数据存入session中
            session.setAttribute("username",account);
            jsonObject.put("login", "OK");
        }
        else
            jsonObject.put("login","false");
        return jsonObject.toJSONString();
    }

}
