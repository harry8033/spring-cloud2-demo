package com.pf.code.dao;

import java.util.Map;
import java.util.List;

import com.pf.code.entity.DataSource;

public interface DataSourceDao{
	
	List<DataSource> findBy(Map<String, Object> params);
	
	DataSource findById(String id);
	
	long getCount(Map<String, Object> params);
	
	int addEntity(DataSource obj);
	
	int updateEntity(DataSource obj);
	
	int deleteById(String id);
	
}
