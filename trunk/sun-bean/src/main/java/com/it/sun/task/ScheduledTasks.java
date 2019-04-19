package com.it.sun.task;

import com.it.common.uitl.uitls.SystemPath;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 */
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 10000)//10秒一次
    public void reportCurrentTime() {
        System.out.println("执行定时器任务了！");

        System.out.println("SystemPath.getSystempPath():"+SystemPath.getSystempPath());
        System.out.println("SystemPath.getSysPath():"+SystemPath.getSysPath());
        System.out.println("SystemPath.getClassPath():"+SystemPath.getClassPath());
        System.out.println("SystemPath.getSeparator():"+SystemPath.getSeparator());

        //将BaseCaChe中的缓存存储到磁盘



    }
}
