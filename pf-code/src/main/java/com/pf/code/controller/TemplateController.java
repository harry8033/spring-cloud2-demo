package com.pf.code.controller;

import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.core.util.MD5Utils;
import com.pf.shiro.util.PasswordHelper;
import com.pf.spring.base.BaseController;
import com.pf.spring.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pf.code.service.TemplateService;
import com.pf.code.entity.Template;

import java.util.Map;

@RestController
@RequestMapping("/code/template")
public class TemplateController extends BaseController{

	@Autowired
	private TemplateService templateService;
	
	/**分页查询*/
	@RequestMapping(value = "/findBy", method = RequestMethod.GET)
	public Result findByPage() {
		Map<String, Object> params = getRequestParams();
		Page<Template> page = templateService.findByPage(params);
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
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result deleteEntity(){
		String id = getParaValue("id");
		templateService.deleteByIds(new String[]{id});
		return Result.asSuccess();
	}
}