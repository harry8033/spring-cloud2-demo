package com.pf.schedule.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Author: Ru He
 * Date: Created in 2019/2/3.
 * Description:
 */
public class DemoTask implements Job,Serializable {

    private final static Logger log = LoggerFactory.getLogger(DemoTask.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("------> 任务已执行。");
    }
}
