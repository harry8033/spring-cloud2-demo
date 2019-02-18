package com.pf.bigdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pf.core.util.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ru He
 * Date: Created in 2019/2/1.
 * Description:
 */
@JsonIgnoreProperties
public class ImportTable {

    private String id;
    /*表名*/
    private String sqlName;
    /*字段列表*/
    private List<ImportColumn> columns = new ArrayList();
    /*备注*/
    private String remarks;
    /*表创建语句*/
    private String ddlSql;
    /*主键列表*/
    private List primaryKeyColumns;

    public ImportTable(){
        this.id = Common.getUUID();
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImportColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ImportColumn> columns) {
        this.columns = columns;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTableName(){
        return this.sqlName;
    }

    public String getDdlSql() {
        return ddlSql;
    }

    public void setDdlSql(String ddlSql) {
        this.ddlSql = ddlSql;
    }

    public List getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    public void setPrimaryKeyColumns(List primaryKeyColumns) {
        this.primaryKeyColumns = primaryKeyColumns;
    }

    public List getPks(){
        if(this.primaryKeyColumns == null){
            this.primaryKeyColumns = new ArrayList();
        }
        return this.primaryKeyColumns;
    }
}
