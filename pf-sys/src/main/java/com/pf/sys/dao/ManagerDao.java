package com.pf.sys.dao;


import com.pf.core.entity.Param;
import com.pf.sys.entity.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
	
	List<Manager> findBy(Map<String, Object> params);
	
	Manager findById(String id);
	
	long getCount(Map<String, Object> params);
	
	void addEntity(Manager obj);
	
	void updateEntity(Manager obj);
	
	void deleteById(List<String> ids);
	
	Manager findByAccount(String account);

	void updatePwd(Param params);
	
}
