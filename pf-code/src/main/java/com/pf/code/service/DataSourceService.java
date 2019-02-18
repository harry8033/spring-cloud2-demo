package com.pf.code.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.pf.spring.entity.Page;
import com.pf.code.entity.DataSource;
import com.pf.code.dao.DataSourceDao;

@Service
public class DataSourceService{

	@Autowired
	private DataSourceDao dataSourceDao;
	
	/**条件查询*/
	public List<DataSource> findBy(Map<String, Object> params) {
        return dataSourceDao.findBy(params);
    }
	
	/**根据主键查询*/
	public DataSource findById(String id) {
        return dataSourceDao.findById(id);
    }
	
	/**分页查询*/
	public Page<DataSource> findByPage(Map<String, Object> params) {
    	Page<DataSource> page = new Page<DataSource>(params);
		page.setRows(dataSourceDao.findBy(params));
		page.setTotal(dataSourceDao.getCount(params));
        return page;
    }
	
	/**
	 * 新增
	 */
	public void addEntity(DataSource dataSource){
		dataSourceDao.addEntity(dataSource);
	}
	
	/**
	 * 修改
	 */
	public void updateEntity(DataSource dataSource){
		dataSourceDao.updateEntity(dataSource);
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteByIds(String[] ids){
		if(ids != null){
			for(String id : ids){
				dataSourceDao.deleteById(id);
			}
		}
	}
}
