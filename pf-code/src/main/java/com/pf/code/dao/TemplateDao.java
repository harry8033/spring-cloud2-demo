package com.pf.code.dao;

import java.util.Map;
import java.util.List;

import com.pf.code.entity.Template;

public interface TemplateDao{
	
	List<Template> findBy(Map<String, Object> params);
	
	Template findById(String id);
	
	long getCount(Map<String, Object> params);
	
	int addEntity(Template obj);
	
	int updateEntity(Template obj);
	
	int deleteById(String id);
	
}
