package com.pf.sys.service;

import com.pf.core.entity.Param;
import com.pf.core.util.Common;
import com.pf.core.util.Constants;
import com.pf.core.util.Md5Utils;
import com.pf.shiro.util.PasswordHelper;
import com.pf.spring.entity.Page;
import com.pf.sys.aspectj.annotation.ModuleLog;
import com.pf.sys.aspectj.enums.OptType;
import com.pf.sys.dao.ManagerDao;
import com.pf.sys.dao.PrivilegeDao;
import com.pf.sys.dao.RoleDao;
import com.pf.sys.entity.Manager;
import com.pf.sys.entity.ManagerRoleRela;
import com.pf.sys.entity.Menu;
import com.pf.sys.entity.Privilege;
import org.springframework.beans.BeanUtils;
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

	/**
	 * 功能描述: 分页获取管理员信息
	 * @auther Ru He
	 * @param params [limit, offset] [每页行数，开始位置]
	 * @return 返回分页数据
	 * @date 2019/1/30 下午9:58
	 */
	public Page<Manager> findByPage(Map<String, Object> params) {
    	Page<Manager> page = new Page<>(params);
		page.setRows(managerDao.findBy(params));
		page.setTotal(managerDao.getCount(params));
        return page;
    }

	/**
	 * 新增
	 */
	@Transactional(rollbackFor = Exception.class)
	@ModuleLog(module = "管理员管理", type = OptType.SAVE)
	public void addEntity(Manager dmanager){
		dmanager.setId(Common.getUUID());
		String[] str = PasswordHelper.encryptPassword(Md5Utils.getMD5("000000".getBytes()), dmanager.getAccount());
		dmanager.setPwd(str[0]);
		dmanager.setSalt(str[1]);
		managerDao.addEntity(dmanager);
		updateUserRoleRela(dmanager);
	}

	@Transactional(rollbackFor = Exception.class)
	@ModuleLog(module = "管理员管理", type = OptType.SAVE)
	public void updateEntity(Manager dmanager){
		//String[] str = PasswordHelper.encryptPassword(dmanager.getPwd(), dmanager.getAccount());
		//dmanager.setPwd(str[0]);
		//dmanager.setSalt(str[1]);
		managerDao.updateEntity(dmanager);
		updateUserRoleRela(dmanager);
	}

	private void updateUserRoleRela(Manager dmanager){
		if(!Common.isEmpty(dmanager.getRoles())){
			Map<String, Object> params = new HashMap<>(2);
			params.put("managerid", dmanager.getId());
			List<ManagerRoleRela> list = new ArrayList<>();
			for(String roleid : dmanager.getRoles().split(Constants.SYMBOL_DH)){
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
	@Transactional(rollbackFor = Exception.class)
	public void deleteByIds(List<String> ids){
		managerDao.deleteById(ids);
	}

	/**
	 * 功能描述: 根据账号获取用户信息
	 * @auther Ru He
	 * @param account 帐号
	 * @return 返回管理员信息
	 * @date 2019/1/30 下午10:01
	 */
	public Manager findByAccount(String account){
		return managerDao.findByAccount(account);
	}

	public List<Menu> getMenus(String managerId) {
		List<Privilege> list = privDao.getMenusByUser(managerId);
		List<Menu> menus = new ArrayList<>();
		for(Privilege priv : list){
			if("#".equals(priv.getPid())){
				Menu menu = new Menu();
				BeanUtils.copyProperties(priv, menu);
				getSubMenus(menu, list);
				menus.add(menu);
			}
		}
		return menus;
	}

	private void getSubMenus(Menu menu, List<Privilege> list){
		for(Privilege priv : list){
			if(priv.getPid().equals(menu.getId())){
				Menu sub = new Menu();
				BeanUtils.copyProperties(priv, sub);
				menu.getChildren().add(sub);
				getSubMenus(sub, list);
			}
		}
	}

	public void updatePwd(Param params){
		managerDao.updatePwd(params);
	}
}
