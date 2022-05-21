package com.example.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.software.Dao.videoDao;
import com.example.software.Model.video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class videoController {
    @Autowired
    private videoDao videoDao;

    @RequestMapping(value = "insertv")
    @ResponseBody
    public String insertv(String title,String href,String timelong)
    {
        JSONObject jsonObject = new JSONObject();
        video video = new video(title,href,timelong);
        int res = videoDao.inserVide(video);
        if(res == 1)jsonObject.put("res",res);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "findallv")
    @ResponseBody
    public String findallv()
    {
        JSONObject jsonObject = new JSONObject();
        List<video> list = videoDao.findall();
        if(list!=null)jsonObject.put("res",list);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "removeall")
    @ResponseBody
    public String removeall()
    {
        JSONObject jsonObject = new JSONObject();
        int res = videoDao.removeall();
        if(res==1)jsonObject.put("res",res);
        return jsonObject.toJSONString();
    }
}
