package com.moqu.manage.dao;

import com.moqu.manage.entity.Privilege;

import java.util.List;
import java.util.Map;

 public interface PrivilegeDao {
	
	List<Privilege> selectAll();

	List<Map<String, String>> selectTree();

	List<Map<String, String>> selectByRole(String roleid);
	
	List<Privilege> getMenusByUser(String managerId);
}
