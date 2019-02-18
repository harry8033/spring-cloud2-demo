package com.pf.sys.dao;

import java.util.Map;
import java.util.List;

import com.pf.sys.entity.OptLog;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: OptLog数据库层
 */
public interface OptLogDao{
	/**
	 * 功能描述: 根据条件获取列表数据
	 * @param params 参数map
	 * @return 列表数据
	 */
	List<OptLog> findBy(Map<String, Object> params);

	/**
	 * 功能描述: 根据主键获取单条数据
	 * @param id 主键
	 * @return 实例对象
	 */
	OptLog findById(String id);

	/**
	 * 功能描述: 根据条件获取查询行数
	 * @param params 参数map
	 * @return 行数
	 */
	long getCount(Map<String, Object> params);

	/**
	 * 功能描述: 保存实例
	 * @param obj 实例
	 * @return 执行成功条数
	 */
	int addEntity(OptLog obj);

	/**
	 * 功能描述: 更新实例
	 * @param obj 实例
	 * @return 执行成功条数
	 */
	int updateEntity(OptLog obj);

	/**
	 * 功能描述: 根据主键删除实例
	 * @param id 主键
	 * @return 执行成功条数
	 */
	int deleteById(List<String> id);
	
}
