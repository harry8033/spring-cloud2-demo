package com.pf.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.code.entity.Template;
import com.pf.code.service.TemplateService;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Ru He
 * Date: 2019/1/29
 * Description: 模板管理控制器
 */
@RestController
@RequestMapping("/code/template")
public class TemplateController extends BaseController{

	@Autowired
	private TemplateService templateService;
	
	/**分页查询*/
	@RequestMapping(value = "/findBy", method = RequestMethod.POST)
	public Result findByPage(@RequestBody Param params) {
        PageHelper.startPage(params.getIntValue("page"),
                params.getIntValue("size"));
		PageInfo<Template> page = new PageInfo<>(templateService.findBy(params));
        return new Result().setData(page);
    }

	/**
	 * 新增or更新
	 */
	@ResponseBody
	@RequestMapping(value = "/saveEntity", method = RequestMethod.POST)
	public Result saveEntity(@RequestBody Template entity){
		if(!Common.isEmpty(entity.getId())){
			templateService.updateEntity(entity);
		}else{
			templateService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@PostMapping(value = "/delete")
	public Result deleteEntity(@RequestBody Param params){
		templateService.deleteByIds(params.getList("ids", String.class));
		return Result.asSuccess();
	}
}