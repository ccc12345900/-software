package com.example.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.software.Dao.ItemDao;
import com.example.software.Model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Controller
public class bookController {
    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "insert")
    @ResponseBody
    public String inssgs()
    {
        book book = new book("小王子","asfdasdf");
        int t = itemDao.insertBook(book);
        if(t == 1)return "true";
        else return "false";
    }

    @RequestMapping(value = "query")
    @ResponseBody
    public String findcontent(String name,int index)
    {
        System.out.println(name);
        System.out.println(index);
        JSONObject jsonObject = new JSONObject();
        List<book> list = itemDao.findBooks(name);
        String path = list.get(index).getPath();
        String res = new String();
        if(path!=null){
            File file = new File(path);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    res+=tempString;
                    line++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
            System.out.println(file.getAbsolutePath());
            jsonObject.put("content",res);
        }
        else jsonObject.put("content","空");
        return jsonObject.toJSONString();
    }
}
