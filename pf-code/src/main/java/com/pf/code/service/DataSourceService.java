package com.pf.code.service;

import com.pf.code.dao.DataSourceDao;
import com.pf.code.entity.DataSource;
import com.pf.core.entity.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataSourceService{

	private final Logger log = LoggerFactory.getLogger(DataSourceService.class);

	@Autowired
	private DataSourceDao dataSourceDao;
	
	/**条件查询*/
	public List<DataSource> findBy(Param params) {
        return dataSourceDao.findBy(params);
    }
	
	/**根据主键查询*/
	public DataSource findById(Integer id) {
        return dataSourceDao.findById(id);
    }

	/**
	 * 新增
	 */
	public void addEntity(DataSource dataSource){
		dataSource.setDriverclass(getDriverClass(dataSource.getDbtype()));
		dataSourceDao.addEntity(dataSource);
	}
	
	/**
	 * 修改
	 */
	public void updateEntity(DataSource dataSource){
		dataSource.setDriverclass(getDriverClass(dataSource.getDbtype()));
		dataSourceDao.updateEntity(dataSource);
	}
	
	/**
	 * 删除
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteByIds(List<String> ids){
		dataSourceDao.deleteById(ids);
	}

	private String getDriverClass(String dbtype){
		if("mysql".equalsIgnoreCase(dbtype)){
			return "com.mysql.jdbc.Driver";
		}else if("mysql".equalsIgnoreCase(dbtype)){
			return "oracle.jdbc.driver.OracleDriver";
		}
		return "";
	}
}
