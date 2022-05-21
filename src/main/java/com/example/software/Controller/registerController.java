package com.example.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.software.Service.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class registerController {
    @Autowired
    registerService registerService;

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public String register(String account,String password,String repass)
    {
        System.out.println("注册中...");
        JSONObject jsonObject = new JSONObject();
        if(!password.equals(repass))
        {
            System.out.println(password+" "+repass);
            jsonObject.put("register","NO");
            return jsonObject.toJSONString();
        }else{
            int res = registerService.registerservice(account,password);
            if(res == 1)
            {
                jsonObject.put("register","OK");
            }else{
                jsonObject.put("register","NO");
            }
            System.out.println("插入失败");
            return jsonObject.toJSONString();
        }
    }
}
