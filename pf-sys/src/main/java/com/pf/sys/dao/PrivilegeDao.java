package com.pf.sys.dao;

import com.pf.sys.entity.Privilege;

import java.util.List;
import java.util.Map;

 public interface PrivilegeDao {
 	List<Privilege> selectAll();

	List<Map<String, String>> selectTree();

	List<Map<String, String>> selectByRole(String roleid);
	
	List<Privilege> getMenusByUser(String managerId);

	List<Privilege> findBy(Map<String, Object> params);

	Privilege findById(String id);

	long getCount(Map<String, Object> params);

	int addEntity(Privilege obj);

	 int updateEntity(Privilege obj);

	 int deleteById(List<String> ids);
}
