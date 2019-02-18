package com.pf.sys.service;

import com.pf.core.entity.Param;
import com.pf.sys.dao.PrivilegeDao;
import com.pf.sys.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;
	
	/**条件查询*/
	public List<Privilege> findBy(Param params) {
        return privilegeDao.findBy(params);
    }
	
	/**根据主键查询*/
	public Privilege findById(String id) {
        return privilegeDao.findById(id);
    }
	
	/**
	 * 新增
	 */
	public void addEntity(Privilege tprivilege){
		privilegeDao.addEntity(tprivilege);
	}
	
	/**
	 * 修改
	 */
	public void updateEntity(Privilege tprivilege){
		privilegeDao.updateEntity(tprivilege);
	}
	
	/**
	 * 删除
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteByIds(List<String> ids){
		privilegeDao.deleteById(ids);
	}
}
