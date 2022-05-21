package com.example.software.Task;

import com.example.software.Model.book;
import com.example.software.Service.impl.ItemServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class bookProcessor implements PageProcessor {
    @Autowired
    private ItemServiceimpl itemServiceimpl;
    public static bookProcessor componentClass;
    @PostConstruct
    public void init(){
        componentClass = this;
        componentClass.itemServiceimpl = this.itemServiceimpl;
    }

    @Override
    public void process(Page page) {
//得到url，起始为http://www.147xs.org/，首页
        String url=page.getUrl().get();
        //通过分析知道带有html的url是之前获取到的每个章节的链接。
        if(url.contains(".html")) {//如果是章节的链接则进入
            this.getContent(page);//抓取内容函数
        }
//        else if(url.equals("http://www.147xs.org/")){//如果是网站首页则进入，获取所有小说的链接
//            List<String> links = page.getHtml().links()
//                    .regex(".*book/\\d+/").all();//正则匹配url
//            page.addTargetRequests(links);//加入请求队列等待处理
//            page.setSkip(true);//跳过保存管道类
//        }
        else {
            //获取该小说的所有章节的链接
            List<String> links = page.getHtml().links()
                    .regex(".*\\d+\\.html").all();//正则匹配url
            page.addTargetRequests(links);//加入请求队列等待处理
            page.setSkip(true);//跳过保存管道类
        }
    }

    public void getContent(Page page){
        //书名
        String bookname=page.getHtml()
                .xpath("//div[@class='con_top']/a[3]/text()").get();
        //标题
        String title=page.getHtml()
                .xpath("//div[@class='bookname']/h1/text()").get();
        //内容
        List<String> all = page.getHtml()
                .xpath("//div[@id='content']//p/text()").all();
        //通过键值对的方式存入，用于后面写入文件时用到这些属性
        page.putField("bookname",bookname);
        page.putField("title",title.replaceAll("[/?\\\\]",""));
        page.putField("content",all);
        book book = new book(bookname,title,"E:\\webmagic\\"+bookname+"\\"+title+".txt");
        componentClass.itemServiceimpl.Save(book);
        System.out.println(book.getPath());
    }


    private Site site = Site.me()
            .setCharset("utf8")
            .setTimeOut(10*1000)
            .setRetrySleepTime(3000)
            .setRetryTimes(3);
    @Override
    public Site getSite() {
        return site;
    }

    //initialDelay任务启动后等待多久执行
    //fixedDelay每隔多久执行

    public  void progress()
    {
        String url = "https://www.147xs.org/book/4757/";
        Spider.create(new bookProcessor()).addUrl(url)
                .addPipeline(new SavePipeline())
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
