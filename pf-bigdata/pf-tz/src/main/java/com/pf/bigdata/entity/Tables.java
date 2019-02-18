
package com.pf.bigdata.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: 实体类
 */
public class Tables {
	

	/**/
	private String id;
	/*表名*/
	private String tableName;
	/*表描述*/
	private String tableComm;
	/*列数量*/
	private Integer colSize;
	/*创建语句*/
	private String ddlSql;
	private String ctime;

	private List<Column> columns;

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComm() {
		return this.tableComm;
	}
	
	public void setTableComm(String tableComm) {
		this.tableComm = tableComm;
	}
	public Integer getColSize() {
		return this.colSize;
	}
	
	public void setColSize(Integer colSize) {
		this.colSize = colSize;
	}
	public String getDdlSql() {
		return this.ddlSql;
	}
	
	public void setDdlSql(String ddlSql) {
		this.ddlSql = ddlSql;
	}
	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<String> getPks(){
		List<String> pks = new ArrayList<>();
		if(this.columns != null && this.columns.size() > 0){
			for(Column c : this.columns){
				if(c.getIspk() != null && c.getIspk() == 1){
					pks.add(c.getColName());
				}
			}
		}
		return pks;
	}
}

