package com.pf.schedule.service;

import com.pf.core.entity.Param;
import com.pf.schedule.dao.TaskDao;
import com.pf.schedule.entity.Task;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: Task服务类
 */
@Service
public class TaskService{

    private final static Logger log = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private TaskDao taskDao;

	@Autowired
    private Scheduler scheduler;
	
	/**
	 * 功能描述: 根据条件查询列表数据
	 * @param params 参数map
	 * @return 查询结果
	 * @date 2019/2/3 下午11:46
	 */
	public List<Task> findBy(Param params) {
        return taskDao.findBy(params);
    }

	/**
	 * 功能描述: 根据主键查询单行数据
	 * @param id 主键
	 * @return 实例
	 * @date 2019/2/3 下午11:46
	 */
	public Task findById(String id) {
        return taskDao.findById(id);
    }

	/**
	 * 功能描述: 保存实例到数据库
	 * @param task 实例
	 * @date 2019/2/3 下午11:46
	 */
	public void addJob(Task task) throws Exception{
        JobKey key = new JobKey(task.getJobName(), task.getJobGroup());
        //如果job存在，则先移除
        if(scheduler.getJobDetail(key) != null){
            scheduler.deleteJob(key);
        }
        Class cls = Class.forName(task.getJobClassName()) ;
        cls.newInstance();
        //构建job信息
        JobDetail job = JobBuilder.newJob(cls).withIdentity(task.getJobName(),
                task.getJobGroup())
                .withDescription(task.getDescription()).build();
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+task.getJobName(), task.getJobGroup())
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
	}

	/**
	 * 功能描述: 更新实例到数据库
	 * @param task 实例
	 * @date 2019/2/3 下午11:46
	 */
	public void editJob(Task task){
		taskDao.updateEntity(task);
	}

	/**
	 * 功能描述: 根据主键删除数据
	 * @param task 任务信息
	 * @date 2019/2/3 下午11:46
	 */
	public void deleteJob(Task task) throws Exception{
		JobKey key = new JobKey(task.getJobName(), task.getJobGroup());
		if (scheduler.getJobDetail(key) != null){
            scheduler.deleteJob(key);
        }
	}

	/**
	 * 功能描述: 恢复任务
	 * @auther Ru He
	 * @param task 任务信息
	 * @date 2019/2/3 下午11:46
	 */
	public void resumeJob(Task task) throws Exception{
        JobKey key = new JobKey(task.getJobName(), task.getJobGroup());
        scheduler.resumeJob(key);
    }
    
    /**
     * 功能描述: 暂停任务
     * @auther Ru He
     * @param task 任务信息
     * @date 2019/2/3 下午11:47
     */
    public void pauseJob(Task task) throws Exception{
        JobKey key = new JobKey(task.getJobName(), task.getJobGroup());
        scheduler.pauseJob(key);
    }

    /**
     * 功能描述: 触发任务
     * @auther Ru He
     * @param task 任务信息
     * @date 2019/2/3 下午11:47
     */
    public void triggerJob(Task task) throws Exception{
        JobKey key = new JobKey(task.getJobName(), task.getJobGroup());
        scheduler.triggerJob(key);
    }
}
