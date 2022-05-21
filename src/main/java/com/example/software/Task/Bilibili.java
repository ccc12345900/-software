package com.example.software.Task;

import com.example.software.Dao.videoDao;
import com.example.software.Model.video;
import com.example.software.Service.impl.ItemServiceimpl;
import com.example.software.Service.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Bilibili implements PageProcessor {
    @Autowired
    private videoService videoService;
    public static Bilibili componentClass;
    @PostConstruct
    public void init(){
        componentClass = this;
        componentClass.videoService = this.videoService;
    }

    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);

    public void process(Page page) {
        Html html = page.getHtml();
        Selectable li = html.xpath("//li[@class='video-item matrix']");

        Selectable href = li.xpath("//li/a/@href");
        Selectable title = li.xpath("//li/a/@title");
        Selectable upTime = li.xpath("//span[@title='上传时间']/text()");
        Selectable timeLong = li.xpath("//span[@class='so-imgTag_rb']/text()");

        List<String> hrefs = href.all();
        List<String> titles = title.all();
        List<String> upTimes = upTime.all();
        List<String> timeLongs = timeLong.all();
        List<video> list = componentClass.videoService.findallService();
        if(list!=null)
            componentClass.videoService.removeallService();
        for (int i = 0; i < hrefs.size(); i++) {
            String tL = timeLongs.get(i);
            String tl = titles.get(i);
            String hf = hrefs.get(i);
            String uT = upTimes.get(i);
            video video = new video(tl,hf,tL);
            componentClass.videoService.inserVideService(video);
            System.out.println("视频标题：" + tl.trim());
            System.out.println("视频链接：" + hf.replace("//","").trim());
            System.out.println("更新时间：" + uT.trim());
            System.out.println("视频时长：" + tL.trim());
            System.out.println();
        }
    }

    public Site getSite() {
        return site;
    }

//    @Scheduled(initialDelay = 1000,fixedDelay = 10*1000)
    @Scheduled(cron = "0 0 12 ? * WED")
    public  void progress() {
        Spider.create(new Bilibili()).addUrl("https://search.bilibili.com/video?keyword=%E9%87%91%E9%93%B2%E9%93%B2%E4%B9%8B%E6%88%98&from_source=nav_search_new")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))) //参数设置需要对多少条数据去重
                .thread(5)
                .run();
    }
}
