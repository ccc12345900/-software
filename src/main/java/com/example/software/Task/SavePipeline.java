package com.example.software.Task;

import com.example.software.Model.book;
import com.example.software.Service.impl.ItemServiceimpl;
import com.example.software.utils.SpringContextUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Component
class SavePipeline implements Pipeline{
    public void process(ResultItems resultItems, Task task) {
        //通过key获取值
        String bookname=resultItems.get("bookname");
        String title = resultItems.get("title");
        List<String> contents=resultItems.get("content");
        //创建小说目录
        File file=new File("E:\\webmagic\\"+bookname);
        if(!file.exists()){
            file.mkdirs();
        }
        //写入文件
        try {
            FileOutputStream writer=new FileOutputStream(
                    file.getAbsolutePath()+"\\"+title+".txt");
            //遍历集合，取出每段内容
            for (String content : contents) {
                String replace = content.replace("。", "\n");
                writer.write(replace.getBytes(StandardCharsets.UTF_8));
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

