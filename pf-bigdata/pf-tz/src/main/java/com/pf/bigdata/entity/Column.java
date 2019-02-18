
package com.pf.bigdata.entity;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: 实体类
 */
public class Column {
	

	/**/
	private Integer id;
	/*关联t_list_table.id*/
	private String tableId;
	/*列名*/
	private String colName;
	/*列描述*/
	private String colRemarks;
	/*列类型，包括长度经度*/
	private String colType;
	/*列默认值*/
	private String colDef;
	/*是否可空：0、否；1、是*/
	private Integer isnull;
	/*是否主键：0、否；1、是；*/
	private Integer ispk;
	private String ctime;


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getTableId() {
		return this.tableId;
	}
	
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getColName() {
		return this.colName;
	}
	
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColRemarks() {
		return this.colRemarks;
	}
	
	public void setColRemarks(String colRemarks) {
		this.colRemarks = colRemarks;
	}
	public String getColType() {
		return this.colType;
	}
	
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getColDef() {
		return this.colDef;
	}
	
	public void setColDef(String colDef) {
		this.colDef = colDef;
	}
	public Integer getIsnull() {
		return this.isnull;
	}
	
	public void setIsnull(Integer isnull) {
		this.isnull = isnull;
	}
	public Integer getIspk() {
		return this.ispk;
	}
	
	public void setIspk(Integer ispk) {
		this.ispk = ispk;
	}
	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
}

