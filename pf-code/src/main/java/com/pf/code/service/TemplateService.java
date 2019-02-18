package com.pf.code.service;

import com.pf.code.dao.TemplateDao;
import com.pf.code.entity.Template;
import com.pf.core.entity.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Ru He
 * Description: 代码生成模板Service类
 */
@Service
public class TemplateService{

	@Autowired
	private TemplateDao templateDao;
	
	/**
	 * 功能描述: 根据条件获取模板列表
	 * @auther Ru He
	 * @param params 查询条件
	 * @return 模板信息
	 * @date 2019/2/2 上午9:07
	 */
	public List<Template> findBy(Param params) {
        return templateDao.findBy(params);
    }
	
	/**
	 * 功能描述: 根据模板ID获取模板信息
	 * @auther Ru He
	 * @param id 模板ID
	 * @return 模板信息
	 * @date 2019/2/2 上午9:07
	 */
	public Template findById(String id) {
        return templateDao.findById(id);
    }
	
	/**
	 * 功能描述: 添加模板信息
	 * @auther Ru He
	 * @param template 模板信息
	 * @date 2019/2/2 上午9:06
	 */
	public void addEntity(Template template){
		templateDao.addEntity(template);
	}
	
	/**
	 * 功能描述: 更新模板信息
	 * @auther Ru He
	 * @param template 模板信息
	 * @date 2019/2/2 上午9:05
	 */
	public void updateEntity(Template template){
		templateDao.updateEntity(template);
	}
	
	/**
	 * 功能描述: 根据主键批量删除模板信息
	 * @auther Ru He
	 * @param ids 主键数组
	 * @date 2019/2/2 上午9:04
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteByIds(List<String> ids){
		templateDao.deleteById(ids);
	}
}
