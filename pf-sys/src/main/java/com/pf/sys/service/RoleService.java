package com.pf.sys.service;

import com.pf.core.util.Common;
import com.pf.core.util.Constants;
import com.pf.sys.aspectj.annotation.ModuleLog;
import com.pf.sys.aspectj.enums.OptType;
import com.pf.sys.dao.PrivilegeDao;
import com.pf.sys.dao.RoleDao;
import com.pf.sys.entity.Role;
import com.pf.sys.entity.RolePrivilegeRela;
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
	
	/**
	 * 新增
	 */
	@ModuleLog(module = "角色管理", type = OptType.SAVE)
	public void addEntity(Role role){
		role.setId(Common.getUUID());
		roleDao.addEntity(role);
		updateRolePrivRela(role);
	}
	
	/**
	 * 修改
	 */
	@ModuleLog(module = "角色管理", type = OptType.EDIT)
	public void updateEntity(Role role){
		roleDao.updateEntity(role);
		updateRolePrivRela(role);
	}
	
	private void updateRolePrivRela(Role role){
		Map<String, Object> params = new HashMap<>(2);
		params.put("roleid", role.getId());
		if(!Common.isEmpty(role.getPrivileges())){
			List<RolePrivilegeRela> list = new ArrayList<>();
			for(String id : role.getPrivileges().split(Constants.SYMBOL_DH)){
				RolePrivilegeRela rp = new RolePrivilegeRela();
				rp.setRoleid(role.getId());
				rp.setPrivilegeid(id);
				list.add(rp);
			}
			params.put("list", list);
		}
		roleDao.updateRolePrivRela(params);
	}
	
	/**
	 * 删除
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteByIds(List<String> ids){
		roleDao.deleteById(ids);
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
