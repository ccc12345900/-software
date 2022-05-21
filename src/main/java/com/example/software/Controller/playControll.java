package com.example.software.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class playControll {
    @RequestMapping(value = "quanhuang")
    public String quanhuang()
    {
        return "KOF";
    }

    @RequestMapping(value = "plantfight")
    public String plantfight()
    {
        return "plant";
    }
    //小说主页
    @RequestMapping(value = "xindex")
    public String xindex()
    {
        return "list";
    }
    //主页
    @RequestMapping(value = "mindex")
    public String mindex()
    {
        return "allindex";
    }
    //登录后的主页
    @RequestMapping(value = "m1index")
    public String m1index()
    {
        return "1";
    }
    @RequestMapping(value = "m2index")
    public String m2index()
    {
        return "2";
    }
    @RequestMapping(value = "m3index")
    public String m3index()
    {
        return "3";
    }
    @RequestMapping(value = "personal")
    public String personal()
    {
        return "personal";
    }
    @RequestMapping(value = "readw")
    public String readw(){return "read";}
}
