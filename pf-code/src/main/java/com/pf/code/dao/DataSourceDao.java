package com.pf.code.dao;

import com.pf.code.entity.DataSource;

import java.util.List;
import java.util.Map;

public interface DataSourceDao{
	
	List<DataSource> findBy(Map<String, Object> params);

	List<Map> findSimpleBy();
	
	DataSource findById(Integer id);
	
	long getCount(Map<String, Object> params);
	
	int addEntity(DataSource obj);
	
	int updateEntity(DataSource obj);
	
	int deleteById(List<String> id);
	
}
