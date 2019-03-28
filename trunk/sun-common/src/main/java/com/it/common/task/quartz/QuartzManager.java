package com.it.common.task.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

public class QuartzManager {

    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName  任务名
     * @param jobClass 任务
     * @param time     时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, String jobClass, String time) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob((Class)Class.forName(jobClass)).withIdentity(jobName,JOB_GROUP_NAME).build();// new JobDetail(jobName, JOB_GROUP_NAME, Class.forName(jobClass));// 任务名，任务组，任务执行类
            // 触发器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);//触发器时间设定
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME).withSchedule(cronScheduleBuilder).build();;// new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组

            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @param time
     */
    public static void modifyJobTime(String jobName, String time) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey tk=new TriggerKey(jobName, TRIGGER_GROUP_NAME);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(tk);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                JobKey jk=new JobKey(jobName, JOB_GROUP_NAME);
                JobDetail jobDetail = sched.getJobDetail(jk);
                Class objJobClass = jobDetail.getJobClass();
                String jobClass = objJobClass.getName();
                removeJob(jobName);

                addJob(jobName, jobClass, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     */
    public static void removeJob(String jobName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey tk=new TriggerKey(jobName, TRIGGER_GROUP_NAME);
            sched.pauseTrigger(tk);// 停止触发器
            sched.unscheduleJob(tk);// 移除触发器
            JobKey jk=new JobKey(jobName, JOB_GROUP_NAME);
            sched.deleteJob(jk);// 删除任务
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务（关闭后任务就全部消失了，要重启时，需要重新添加任务）
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        QuartzManager.addJob("调度信息","com.it.sun.task.quartz.MyJob","10/2 * * * * ?");
//        QuartzManager.addJob("调度信息22222222","com.it.sun.task.quartz.MyJob","10/2 * * * * ?");
//    }
}
