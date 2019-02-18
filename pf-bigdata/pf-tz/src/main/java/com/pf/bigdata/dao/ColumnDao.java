package com.pf.bigdata.dao;

import com.pf.bigdata.entity.Column;

import java.util.List;
import java.util.Map;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: Column数据库层
 */
public interface ColumnDao{
	/**
	 * 功能描述: 根据条件获取列表数据
	 * @param params 参数map
	 * @return 列表数据
	 */
	List<Column> findBy(Map<String, Object> params);

	/**
	 * 功能描述: 根据主键获取单条数据
	 * @param id 主键
	 * @return 实例对象
	 */
	Column findById(String id);

	/**
	 * 功能描述: 根据条件获取查询行数
	 * @param params 参数map
	 * @return 行数
	 */
	long getCount(Map<String, Object> params);

	/**
	 * 功能描述: 保存实例
	 * @param cols 字段列表
	 * @return 执行成功条数
	 */
	int addEntity(List<Column> cols);

	/**
	 * 功能描述: 保存实例（包括id）
	 * @param cols 字段列表
	 * @return 执行成功条数
	 */
	int addEntityWithId(List<Column> cols);

	/**
	 * 功能描述: 更新实例
	 * @param obj 实例
	 * @return 执行成功条数
	 */
	int updateEntity(Column obj);

	/**
	 * 功能描述: 根据主键删除实例
	 * @param id 主键
	 * @return 执行成功条数
	 */
	int deleteById(List<String> id);

	/**
	 * 功能描述: 根据表格id删除列
	 * @param tableId 表格id
	 * @return 执行成功条数
	 */
	int deleteByTable(String tableId);
	
}
