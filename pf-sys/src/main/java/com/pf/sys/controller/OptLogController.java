package com.pf.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pf.sys.service.OptLogService;
import com.pf.sys.entity.OptLog;


/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: OptLog控制器
 */
@RestController
@RequestMapping("/sys/optlog")
public class OptLogController extends BaseController{

	@Autowired
	private OptLogService optLogService;
	
	/**
	 * 功能描述: 分页查询数据
	 * @param params 参数
	 * @return 分页数据
	 * @date yyyy/mm/dd
	 */
	@PostMapping(value = "/findByPage")
	public Result findByPage(@RequestBody Param params) {
		PageHelper.startPage(params.getIntValue("page"),
			params.getIntValue("size"));
		PageInfo<OptLog> page = new PageInfo<>(optLogService.findBy(params));
		return new Result().setData(page);
    }

	/**
	 * 功能描述: 新增或更新实例到数据库
	 * @param entity 实例
	 * @return 处理结果
	 * @date yyyy/mm/dd
	 */
	@PostMapping(value = "/saveEntity")
	public Result saveEntity(@RequestBody OptLog entity){
		if(!Common.isEmpty(entity.getId())){
			optLogService.updateEntity(entity);
		}else{
			optLogService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 功能描述: 根据主键删除数据
	 * @return 处理结果
	 * @date yyyy/mm/dd
	 */
	@PostMapping(value = "/delete")
	public Result deleteEntity(@RequestBody Param params){
		optLogService.deleteByIds(params.getList("ids", String.class));
		return Result.asSuccess();
	}
}