package com.pf.schedule.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.schedule.entity.Task;
import com.pf.schedule.service.TaskService;
import com.pf.spring.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Author: Ru He
 * Date: Created in 2019/2/3.
 * Description: Task控制器
 */
@RestController
@RequestMapping("/schedule/task")
public class TaskController extends BaseController{

	@Autowired
	private TaskService taskService;
	
	/**
	 * 功能描述: 分页查询数据
	 * @param params 查询参数
	 * @return 分页数据
	 * @date 2019/2/3
	 */
	@PostMapping(value = "/findByPage")
	public Result findByPage(@RequestBody Param params) {
		PageHelper.startPage(params.getIntValue("page"),
			params.getIntValue("size"));
		PageInfo<Task> page = new PageInfo<>(taskService.findBy(params));
		return new Result().setData(page);
    }

	/**
	 * 功能描述: 新增任务信息
	 * @param task 实例
	 * @return 处理结果
	 * @date 2019/2/3
	 */
	@PostMapping(value = "/addJob")
	public Result addJob(@RequestBody Task task) throws Exception{
		taskService.addJob(task);
		return Result.asSuccess();
	}

	/**
	 * 功能描述: 更新实任务信息
	 * @param task 实例
	 * @return 处理结果
	 * @date 2019/2/3
	 */
	@PostMapping(value = "/editJob")
	public Result editJob(@RequestBody Task task) throws Exception{
		taskService.addJob(task);
		return Result.asSuccess();
	}

	/**
	 * 功能描述: 移除任务
	 * @return 处理结果
	 * @date 2019/2/3
	 */
	@PostMapping(value = "/remove")
	public Result deleteJob(@RequestBody Task task) throws Exception{
		taskService.deleteJob(task);
		return Result.asSuccess();
	}

	/**
	 * 功能描述: 立即触发任务
	 * @return 处理结果
	 * @date 2019/2/3
	 */
	@PostMapping("/trigger")
	public  Result trigger(@RequestBody Task task) throws Exception{
		taskService.triggerJob(task);
		return Result.asSuccess();
	}
	/**
	 * 功能描述: 暂停任务
	 * @return 处理结果
	 * @date 2019/2/3
	 */
	@PostMapping("/pause")
	public  Result pause(@RequestBody Task task) throws Exception{
		taskService.pauseJob(task);
		return Result.asSuccess();
	}
	/**
	 * 功能描述: 恢复任务
	 * @return 处理结果
	 * @date 2019/2/3
	 */
	@PostMapping("/resume")
	public  Result resume(@RequestBody Task task) throws Exception{
		taskService.resumeJob(task);
		return Result.asSuccess();
	}
}