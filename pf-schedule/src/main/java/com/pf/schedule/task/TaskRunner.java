package com.pf.schedule.task;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Author: Ru He
 * Date: Created in 2019/2/3.
 * Description:
 */
@Component
public class TaskRunner implements ApplicationRunner {
    @Autowired
    private Scheduler scheduler;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*String name = "test01";
        String group = "test";
        String desc = "测试任务";
        String clazz = "com.pf.schedule.task.DemoTask";
        String corn = "0/20 * * * * ?";
        Class cls = Class.forName(clazz) ;
        cls.newInstance();
        //构建job信息
        JobDetail job = JobBuilder.newJob(cls).withIdentity(name,
                group)
                .withDescription(desc).build();
        //添加JobDataMap数据
        job.getJobDataMap().put("itstyle", "科帮网欢迎你");
        job.getJobDataMap().put("blog", "https://blog.52itstyle.com");
        job.getJobDataMap().put("data", new String[]{"张三","李四"});
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(corn);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+name, group)
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);*/
    }
}
