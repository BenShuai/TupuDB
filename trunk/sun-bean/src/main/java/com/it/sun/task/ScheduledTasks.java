package com.it.sun.task;

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
    }
}
