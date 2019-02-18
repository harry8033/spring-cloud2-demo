package com.moqu.manage.dao;

import com.moqu.manage.entity.Manager;

import java.util.List;
import java.util.Map;

public interface ManagerDao {
	
	List<Manager> findBy(Map<String, Object> params);
	
	Manager findById(String id);
	
	long getCount(Map<String, Object> params);
	
	void addEntity(Manager obj);
	
	void updateEntity(Manager obj);
	
	void deleteById(String id);
	
	Manager findByAccount(String account);

	void updatePwd(Map<String, String> params);
	
}
