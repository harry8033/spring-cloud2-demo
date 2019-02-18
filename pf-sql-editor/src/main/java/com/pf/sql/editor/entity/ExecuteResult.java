package com.pf.sql.editor.entity;

import com.pf.spring.entity.Page;

/**
 * Author: Ru He
 * Date: Created in 2019/1/30.
 * Description: SQL执行结果类
 */
public class ExecuteResult {

    private int type; //类型：0、查询；1、新增；2、修改；3、删除

    private Page result; //查询结果

    private long updateRows; //影响行数

    private String error; //错误消息

    private long cost; //耗时

    public ExecuteResult(){
        this.type = -1;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Page getResult() {
        return result;
    }

    public void setResult(Page result) {
        this.result = result;
    }

    public long getUpdateRows() {
        return updateRows;
    }

    public void setUpdateRows(long updateRows) {
        this.updateRows = updateRows;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
