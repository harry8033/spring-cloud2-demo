package com.moqu.manage.service;

import com.dindon.core.utils.Common;
import com.dindon.core.utils.Page;
import com.moqu.manage.dao.PrivilegeDao;
import com.moqu.manage.dao.RoleDao;
import com.moqu.manage.entity.Role;
import com.moqu.manage.entity.RolePrivilegeRela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PrivilegeDao privDao;
	
	/**条件查询*/
	public List<Role> findBy(Map<String, Object> params) {
        return roleDao.findBy(params);
    }
	
	/**根据主键查询*/
	public Role findById(String id) {
        return roleDao.findById(id);
    }
	
	/**分页查询*/
	public Page<Role> findByPage(Map<String, Object> params) {
    	Page<Role> page = new Page<Role>(params);
		page.setRows(roleDao.findBy(params));
		page.setTotal(roleDao.getCount(params));
        return page;
    }
	
	/**
	 * 新增
	 */
	public void addEntity(Role role){
		role.setId(Common.getUUID());
		roleDao.addEntity(role);
		updateRolePrivRela(role);
	}
	
	/**
	 * 修改
	 */
	public void updateEntity(Role role){
		roleDao.updateEntity(role);
		updateRolePrivRela(role);
	}
	
	private void updateRolePrivRela(Role role){
		Map<String, Object> params = new HashMap<>();
		params.put("roleid", role.getId());
		if(!Common.isEmpty(role.getPrivileges())){
			List<RolePrivilegeRela> list = new ArrayList<>();
			for(String id : role.getPrivileges().split(",")){
				RolePrivilegeRela dp = new RolePrivilegeRela();
				dp.setRoleid(role.getId());
				dp.setPrivilegeid(id);
				list.add(dp);
			}
			params.put("list", list);
		}
		roleDao.updateRolePrivRela(params);
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteByIds(String[] ids){
		if(ids != null){
			for(String id : ids){
				roleDao.deleteById(id);
			}
		}
	}
	
	/**
	 * 获取权限树
	 */
	public List<?> getPrivileges(){
		return privDao.selectTree();
	}
	
	/**
	 * 获取权限树
	 */
	public List<?> getSelectedPrivileges(String roleid){
		return privDao.selectByRole(roleid);
	}
}
