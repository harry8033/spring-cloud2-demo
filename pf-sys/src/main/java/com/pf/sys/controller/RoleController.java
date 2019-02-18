package com.moqu.manage.controller;


import com.dindon.core.base.BaseController;
import com.dindon.core.utils.Common;
import com.dindon.core.utils.Page;
import com.moqu.manage.entity.Role;
import com.moqu.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgr/role")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String list(){
		return "views/system/role/list";
	}
	
	/**分页查询*/
	@ResponseBody
	@RequestMapping("/findByPage")
	public Page<Role> findByPage() {
		Map<String, Object> params = getRequestParams();
        return roleService.findByPage(params);
    }
	
	/**不分页查询*/
	@ResponseBody
	@RequestMapping("/findBy")
	public List<Role> findBy() {
		Map<String, Object> params = getRequestParams();
        return roleService.findBy(params);
    }

	/**
	 * 新增or更新
	 */
	@ResponseBody
	@RequestMapping("/saveEntity")
	public String addEntity(@RequestBody Role entity){
		if(!Common.isEmpty(entity.getId())){
			roleService.updateEntity(entity);
		}else{
			roleService.addEntity(entity);
		}
		return "success";
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/deleteEntity")
	public String deleteEntity(){
		String id = getParaValue("id");
		roleService.deleteByIds(new String[]{id});
		return "success";
	}

	/**
	 * 获取权限树
	 */
	@ResponseBody
	@RequestMapping("/getPrivTree")
	public List<?> getPrivTree(){
		return roleService.getPrivileges();
	}

	/**
	 * 获取已选择权限树
	 */
	@ResponseBody
	@RequestMapping("/getSelectedPrivTree")
	public List<?> getSelectedPrivTree(){
		String roleid = getParaValue("roleid");
		return roleService.getSelectedPrivileges(roleid);
	}
}