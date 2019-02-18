package com.pf.code.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.pf.spring.entity.Page;
import com.pf.code.entity.Template;
import com.pf.code.dao.TemplateDao;

@Service
public class TemplateService{

	@Autowired
	private TemplateDao templateDao;
	
	/**条件查询*/
	public List<Template> findBy(Map<String, Object> params) {
        return templateDao.findBy(params);
    }
	
	/**根据主键查询*/
	public Template findById(String id) {
        return templateDao.findById(id);
    }
	
	/**分页查询*/
	public Page<Template> findByPage(Map<String, Object> params) {
    	Page<Template> page = new Page<Template>(params);
		page.setRows(templateDao.findBy(params));
		page.setTotal(templateDao.getCount(params));
        return page;
    }
	
	/**
	 * 新增
	 */
	public void addEntity(Template template){
		templateDao.addEntity(template);
	}
	
	/**
	 * 修改
	 */
	public void updateEntity(Template template){
		templateDao.updateEntity(template);
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteByIds(String[] ids){
		if(ids != null){
			for(String id : ids){
				templateDao.deleteById(id);
			}
		}
	}
}
