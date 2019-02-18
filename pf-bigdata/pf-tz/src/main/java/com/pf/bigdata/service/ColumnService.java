package com.pf.bigdata.service;

import com.pf.bigdata.dao.ColumnDao;
import com.pf.bigdata.entity.Column;
import com.pf.core.entity.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: Column服务类
 */
@Service
public class ColumnService{

	@Autowired
	private ColumnDao columnDao;
	
	/**
	 * 功能描述: 根据条件查询列表数据
	 * @param params 参数map
	 * @return 查询结果
	 * @date yyyy/mm/dd hh:mi
	 */
	public List<Column> findBy(Param params) {
        return columnDao.findBy(params);
    }

	/**
	 * 功能描述: 根据主键查询单行数据
	 * @param id 主键
	 * @return 实例
	 * @date yyyy/mm/dd hh:mi
	 */
	public Column findById(String id) {
        return columnDao.findById(id);
    }

	/**
	 * 功能描述: 保存实例到数据库
	 * @param column 实例
	 * @date yyyy/mm/dd hh:mi
	 */
	public void addEntity(List<Column> column){
		columnDao.addEntity(column);
	}

	/**
	 * 功能描述: 更新实例到数据库
	 * @param column 实例
	 * @date yyyy/mm/dd hh:mi
	 */
	public void updateEntity(Column column){
		columnDao.updateEntity(column);
	}

	/**
	 * 功能描述: 根据主键删除数据
	 * @param ids 主键数组
	 * @date yyyy/mm/dd hh:mi
	 */
	@Transactional
	public void deleteByIds(List<String> ids){
		columnDao.deleteById(ids);
	}
}
