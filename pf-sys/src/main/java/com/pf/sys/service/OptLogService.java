package com.pf.sys.service;

import com.pf.core.entity.Param;
import com.pf.sys.dao.OptLogDao;
import com.pf.sys.entity.OptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: OptLog服务类
 */
@Service
public class OptLogService{

	@Autowired
	private OptLogDao optLogDao;
	
	/**
	 * 功能描述: 根据条件查询列表数据
	 * @param params 参数map
	 * @return 查询结果
	 * @date yyyy/mm/dd hh:mi
	 */
	public List<OptLog> findBy(Param params) {
        return optLogDao.findBy(params);
    }

	/**
	 * 功能描述: 根据主键查询单行数据
	 * @param id 主键
	 * @return 实例
	 * @date yyyy/mm/dd hh:mi
	 */
	public OptLog findById(String id) {
        return optLogDao.findById(id);
    }

	/**
	 * 功能描述: 保存实例到数据库
	 * @param optLog 实例
	 * @date yyyy/mm/dd hh:mi
	 */
	public void addEntity(OptLog optLog){
		optLogDao.addEntity(optLog);
	}

	/**
	 * 功能描述: 更新实例到数据库
	 * @param optLog 实例
	 * @date yyyy/mm/dd hh:mi
	 */
	public void updateEntity(OptLog optLog){
		optLogDao.updateEntity(optLog);
	}

	/**
	 * 功能描述: 根据主键删除数据
	 * @param ids 主键数组
	 * @date yyyy/mm/dd hh:mi
	 */
	@Transactional
	public void deleteByIds(List<String> ids){
		optLogDao.deleteById(ids);
	}
}
