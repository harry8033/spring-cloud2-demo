package com.pf.bigdata.service;

import com.pf.bigdata.dao.ColumnDao;
import com.pf.bigdata.dao.TablesDao;
import com.pf.bigdata.entity.Column;
import com.pf.bigdata.entity.ImportTable;
import com.pf.bigdata.entity.Tables;
import com.pf.code.dao.DataSourceDao;
import com.pf.code.db.DbModelFactory;
import com.pf.code.db.DbModelProvider;
import com.pf.code.entity.DataSource;
import com.pf.code.util.FreeMarkerUtil;
import com.pf.core.entity.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: 作者
 * Date: Created in 2019/1/31.
 * Description: Tables服务类
 */
@Service
public class TablesService{

	private final static Logger log = LoggerFactory.getLogger(TablesService.class);

	@Autowired
	private TablesDao tablesDao;

	@Autowired
	private ColumnDao columnDao;

	@Autowired
	private DataSourceDao dataSourceDao;
	
	/**
	 * 功能描述: 根据条件查询列表数据
	 * @auther Ru He
	 * @param params 参数map
	 * @return 查询结果
	 * @date 2019/1/31
	 */
	public List<Tables> findBy(Map<String, Object> params) {
        return tablesDao.findBy(params);
    }

	/**
	 * 功能描述: 根据主键查询单行数据
	 * @auther Ru He
	 * @param id 主键
	 * @return 实例
	 * @date 2019/1/31
	 */
	public Tables findById(String id) {
        return tablesDao.findById(id);
    }

	/**
	 * 功能描述: 根据条件分页查询单行数据
	 * @auther Ru He
	 * @param params 参数map
	 * @return 分页数据
	 * @date 2019/1/31
	 */
	public List<Tables> findByPage(Param params) {
        return tablesDao.findBy(params);
    }

	/**
	 * 功能描述: 保存实例到数据库
	 * @auther Ru He
	 * @param tables 实例
	 * @date 2019/1/31
	 */
	public void addEntity(Tables tables){
		tablesDao.addEntity(tables);
	}

	/**
	 * 功能描述: 更新实例到数据库
	 * @auther Ru He
	 * @param tables 实例
	 * @date 2019/1/31
	 */
	@Transactional
	public void updateEntity(Tables tables){
		List<Column> idList = new ArrayList<>();
		List<Column> cols = new ArrayList<>();
		if(tables.getColumns() != null && tables.getColumns().size() > 0){
			//遍历列
			for(Column c : tables.getColumns()){
				c.setTableId(tables.getId());
				if(c.getId() != null && c.getId() != 0){
					idList.add(c);
				}else{
					cols.add(c);
				}
			}
			//设置字段数量
			tables.setColSize(tables.getColumns().size());
			//生成SQL语句
			tables.setDdlSql(generateSQL(tables));
		}
		//更新表格信息
		tablesDao.updateEntity(tables);
		//删除原来所有的列
		columnDao.deleteByTable(tables.getId());
		if(idList.size() > 0){
			//新增带ID的列，相当于更新数据
			columnDao.addEntity(idList);
		}
		if(cols.size() > 0){
			//新增不带ID的列
			columnDao.addEntityWithId(cols);
		}
	}

	/**
	 * 功能描述: 根据主键删除数据
	 * @auther Ru He
	 * @param ids 主键数组
	 * @date 2019/1/31
	 */
	@Transactional
	public void deleteByIds(String[] ids){
		if(ids != null){
			for(String id : ids){
				tablesDao.deleteById(id);
			}
		}
	}

	/**
	 * 功能描述: 根据数据源主键获取所有表格数据
	 * @auther Ru HeRu He
	 * @param id 主键
	 * @return java.util.List
	 * @date 2019/1/31 下午10:51
	 */
	public List getTables(Integer id){
		DataSource ds = dataSourceDao.findById(id);
		ds.setDbname("xx");
		DbModelProvider provider = DbModelFactory.getProvider(ds);
		try {
			List tables = provider.getAllTables();
			return tables;
		}catch (Exception e){
			log.error("get all table error.", e);
		}
		return null;
	}

	/**
	 * 功能描述: 获取所有数据源信息，只包含id和名称
	 * @auther Ru HeRu He
	 * @return 返回简单的数据源信息
	 * @date 2019/2/1 下午2:51
	 */
	public List getDataSources(){
		return dataSourceDao.findSimpleBy();
	}

	@Transactional
	public void importTable(ImportTable t){
		//生成SQL
		t.setDdlSql(generateSQL(t));
		tablesDao.importTable(t);
	}

	private String generateSQL(Object tables){
		String templateStr = "CREATE TABLE ${tableName} (\n" +
				"<#list columns as c>\n" +
				"\t${c.colName}\t${c.colType}<#if c_has_next>,</#if>\n" +
				"</#list>\n" +
				"<#if (pks?size &gt; 0)>\n" +
				"\t,CONSTRAINT ${tableName} PRIMARY KEY(<#list pks as pk>${pk}<#if pk_has_next>,</#if></#list>)\n" +
				"</#if>" +
				")";
		String ddlSql = FreeMarkerUtil.generate(templateStr, tables);
		return ddlSql;
	}
}
