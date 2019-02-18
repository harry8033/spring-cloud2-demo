package com.pf.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.spring.base.BaseController;
import com.pf.sys.aspectj.annotation.ModuleLog;
import com.pf.sys.aspectj.enums.OptType;
import com.pf.sys.entity.Privilege;
import com.pf.sys.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/privilege")
public class PrivilegeController extends BaseController{

	@Autowired
	private PrivilegeService privilegeService;
	
	/**分页查询*/
	@PostMapping(value = "/findBy")
	public Result findByPage(@RequestBody Param param) {
		PageHelper.startPage(param.getIntValue("page"),
				param.getIntValue("size"));
		PageInfo<Privilege> page = new PageInfo<>(privilegeService.findBy(param));
        return Result.asSuccess().setData(page);
    }

	/**
	 * 新增
	 */
	@PostMapping(value = "/saveEntity")
	@ModuleLog(module = "菜单管理", type = OptType.SAVE)
	public Result saveEntity(@RequestBody Privilege entity){
		privilegeService.addEntity(entity);
		return Result.asSuccess();
	}

	/**
	 * 更新
	 */
	@PostMapping(value = "/updateEntity")
	@ModuleLog(module = "菜单管理", type = OptType.EDIT)
	public Result updateEntity(@RequestBody Privilege entity){
		privilegeService.updateEntity(entity);
		return Result.asSuccess();
	}

	/**
	 * 删除
	 */
	@PostMapping(value = "/delete")
	@ModuleLog(module = "菜单管理", type = OptType.DELETE)
	public Result deleteEntity(@RequestBody Param param){
		privilegeService.deleteByIds(param.getList("ids", String.class));
		return Result.asSuccess();
	}
}