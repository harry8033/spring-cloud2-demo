package com.moqu.manage.service;

import com.dindon.core.utils.Common;
import com.dindon.core.utils.MD5Utils;
import com.dindon.core.utils.Page;
import com.moqu.manage.dao.ManagerDao;
import com.moqu.manage.dao.PrivilegeDao;
import com.moqu.manage.dao.RoleDao;
import com.moqu.manage.entity.Manager;
import com.moqu.manage.entity.ManagerRoleRela;
import com.moqu.manage.entity.Menu;
import com.moqu.manage.entity.Privilege;
import com.moqu.manage.utils.PasswordHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerService{

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PrivilegeDao privDao;

	/**条件查询*/
	public List<Manager> findBy(Map<String, Object> params) {
        return managerDao.findBy(params);
    }

	/**根据主键查询*/
	public Manager findById(String id) {
        return managerDao.findById(id);
    }

	/**分页查询*/
	public Page<Manager> findByPage(Map<String, Object> params) {
    	Page<Manager> page = new Page<>(params);
		page.setRows(managerDao.findBy(params));
		page.setTotal(managerDao.getCount(params));
        return page;
    }

	/**
	 * 新增
	 */
	@Transactional
	public void addEntity(Manager dmanager){
		dmanager.setId(Common.getUUID());
		String[] str = PasswordHelper.encryptPassword(MD5Utils.getMD5("000000".getBytes()), dmanager.getAccount());
		dmanager.setPwd(str[0]);
		dmanager.setSalt(str[1]);
		managerDao.addEntity(dmanager);
		updateUserRoleRela(dmanager);
	}

	/**
	 * 修改
	 */
	@Transactional
	public void updateEntity(Manager dmanager){
		//String[] str = PasswordHelper.encryptPassword(dmanager.getPwd(), dmanager.getAccount());
		//dmanager.setPwd(str[0]);
		//dmanager.setSalt(str[1]);
		managerDao.updateEntity(dmanager);
		updateUserRoleRela(dmanager);
	}

	private void updateUserRoleRela(Manager dmanager){
		if(!Common.isEmpty(dmanager.getRoles())){
			Map<String, Object> params = new HashMap<>();
			params.put("managerid", dmanager.getId());
			List<ManagerRoleRela> list = new ArrayList<>();
			for(String roleid : dmanager.getRoles().split(",")){
				ManagerRoleRela dr = new ManagerRoleRela();
				dr.setManagerid(dmanager.getId());
				dr.setRoleid(roleid);
				list.add(dr);
			}
			params.put("list", list);
			//更新用户角色关联信息
			roleDao.updateUserRoleRela(params);
		}

	}

	/**
	 * 删除
	 */
	@Transactional
	public void deleteByIds(String[] ids){
		if(ids != null){
			for(String id : ids){
				managerDao.deleteById(id);
			}
		}
	}

	/**
	 * 根据账号获取用户信息
	 * @param account
	 * @return
	 */
	public Manager findByAccount(String account){
		return managerDao.findByAccount(account);
	}

	public List<Menu> getMenus(String managerId) throws Exception{
		List<Privilege> list = privDao.getMenusByUser(managerId);
		List<Menu> menus = new ArrayList<>();
		for(Privilege priv : list){
			if("#".equals(priv.getPid())){
				Menu menu = new Menu();
				BeanUtils.copyProperties(menu, priv);
				getSubMenus(menu, list);
				menus.add(menu);
			}
		}
		return menus;
	}

	private void getSubMenus(Menu menu, List<Privilege> list) throws Exception{
		for(Privilege priv : list){
			if(priv.getPid().equals(menu.getId())){
				Menu sub = new Menu();
				BeanUtils.copyProperties(sub, priv);
				menu.getChildren().add(sub);
				getSubMenus(sub, list);
			}
		}
	}

	public void updatePwd(Map<String,String> params){
		managerDao.updatePwd(params);
	}
}
