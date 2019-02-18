package com.pf.code.dao;

import com.pf.code.entity.Template;

import java.util.List;
import java.util.Map;

public interface TemplateDao{
	
	List<Template> findBy(Map<String, Object> params);
	
	Template findById(String id);
	
	long getCount(Map<String, Object> params);
	
	int addEntity(Template obj);
	
	int updateEntity(Template obj);
	
	int deleteById(List<String> ids);
	
}
