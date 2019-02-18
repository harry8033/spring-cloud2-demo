package com.pf.bigdata.entity;

import com.pf.core.util.Common;

import java.util.List;

/**
 * Author: Ru He
 * Date: Created in 2019/2/1.
 * Description:
 */
public class ImportColumn {
    /*字段名*/
    private String sqlName;
    /*字段描述*/
    private String description;
    /*字段类型名称*/
    private String sqlTypeName;
    /*字段长度*/
    private int size;
    /*字段精度*/
    private int decimalDigits;
    /*字段默认值*/
    private String defaultValue;
    /*是否可空：0、否；1、是；*/
    private boolean isNullable;
    /*是否主键：0、否；1、是；*/
    private boolean isPk;

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSqlTypeName() {
        return sqlTypeName;
    }

    public void setSqlTypeName(String sqlTypeName) {
        this.sqlTypeName = sqlTypeName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        isPk = pk;
    }

    public int getNullableInt(){
        return isNullable ? 1 : 0;
    }

    public int getPkInt(){
        return isPk ? 1 : 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getSqlType(){
        if(!Common.sqlTypeExclude(this.sqlTypeName) && this.size > 0){
            if(this.decimalDigits > 0){
                return this.sqlTypeName + "(" + this.size +"," + this.decimalDigits + ")";
            }
            return this.sqlTypeName + "(" + this.size +")";
        }
        return this.sqlTypeName;
    }

    public String getColName(){
        return this.sqlName;
    }

    public String getColType(){
        return getSqlType();
    }

    public int getIspk(){
        return this.getPkInt();
    }
}
