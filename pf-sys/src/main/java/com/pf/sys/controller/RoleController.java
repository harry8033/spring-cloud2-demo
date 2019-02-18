package com.pf.sys.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import com.pf.sys.aspectj.annotation.ModuleLog;
import com.pf.sys.aspectj.enums.OptType;
import com.pf.sys.entity.Role;
import com.pf.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;

	/**分页查询*/
	@PostMapping(value = "/findByPage")
	public Result findByPage(@RequestBody Param param) {
		PageHelper.startPage(param.getIntValue("page"),
				param.getIntValue("size"));
        PageInfo page = new PageInfo(roleService.findBy(param));
        return Result.asSuccess().setData(page);
    }
	
	/**不分页查询*/
	@GetMapping(value = "/findBy")
	public Result<List> findBy() {
		Map<String, Object> params = getRequestParams();
        List<Role> rolse = roleService.findBy(params);
		return Result.asSuccess().setData(rolse);
	}

	/**
	 * 新增or更新
	 */
	@PostMapping(value = "/saveEntity")
	public Result addEntity(@RequestBody Role entity){
		if(!Common.isEmpty(entity.getId())){
			roleService.updateEntity(entity);
		}else{
			roleService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 删除
	 */
	@PostMapping(value = "/delete")
	@ModuleLog(module = "角色管理", type = OptType.DELETE)
	public Result deleteEntity(@RequestBody Param param){
		roleService.deleteByIds(param.getList("ids", String.class));
		return Result.asSuccess();
	}

	/**
	 * 获取权限树
	 */
	@GetMapping(value = "/getPrivTree")
	public List<?> getPrivTree(){
		List<?> list = roleService.getPrivileges();
		return list;
	}

	/**
	 * 获取已选择权限树
	 */
	@GetMapping(value = "/getSelectedPrivTree/{roleid}")
	public Result<List<?>> getSelectedPrivTree(@PathVariable("roleid") String roleid){
		List<?> list = roleService.getSelectedPrivileges(roleid);
		return Result.asSuccess().setData(list);
	}
}