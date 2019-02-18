package com.pf.sql.editor.service;

import com.pf.core.util.Constants;
import com.pf.spring.entity.Page;
import com.pf.sql.editor.dao.SqlCommandDao;
import com.pf.sql.editor.entity.ExecuteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2019/1/30.
 * Description:
 */
@Service
public class SqlCommandService {

    private final static Logger logger = LoggerFactory.getLogger(SqlCommandService.class);

    @Autowired
    private SqlCommandDao commandDao;

    enum SQL_TYPE{
        //select:查询; insert:插入; update:更新; delete:删除; other:其他操作
        select, insert, update, delete, other
    }

    /**
     * 执行sql
     * @param params 参数，包括：query 语句；
     */
    public ExecuteResult action(Map<String, Object> params){
        ExecuteResult r = new ExecuteResult();
        long start = System.currentTimeMillis();
        String sql = processSQL(params.get("query").toString());
        params.put("query", sql);
        SQL_TYPE type = getType(sql);
        try{
            if(SQL_TYPE.select.equals(type)){
                r.setType(0);
                r.setResult(query(params));
            }else if(SQL_TYPE.insert.equals(type)){
                r.setType(1);
                r.setUpdateRows(update(sql));
            }else if(SQL_TYPE.update.equals(type)){
                r.setType(2);
                r.setUpdateRows(update(sql));
            }else if(SQL_TYPE.delete.equals(type)){
                r.setType(3);
                r.setUpdateRows(update(sql));
            }
            r.setCost(System.currentTimeMillis() - start);
        }catch (Exception e){
            logger.error("execute sql error.", e);
            r.setError(e.getMessage());
        }
        return r;
    }

    /**
     * 执行select语句
     * @param params 参数
     */
    private Page query(Map<String, Object> params){
        Page page = new Page(params);
        page.setRows(commandDao.queryStatement(params));
        page.setTotal(commandDao.queryCount(params));
        return page;
    }

    /**
     * 执行insert、update、delete语句
     * @param sql insert、update、delete语句
     */
    public int update(String sql){
        return commandDao.updateStatement(sql);
    }

    /**
     * 校验及处理sql语句
     */
    private String processSQL(String sql){
        //去掉最后一个分号
        if (sql.endsWith(Constants.SYMBOL_FH)){
            sql = sql.substring(0, sql.length() - 1);
        }
        //mysql在执行带注释的语句中一定要在--后加空格
        return sql.replace("--", "-- ");
    }

    /**
     * 获取sql的类型
     * @param sql
     * @return
     */
    private SQL_TYPE getType(String sql){
        String[] lines = sql.split("\n");
        for(String l : lines){
            if(l.trim().startsWith("--")){
                continue;
            }
            if(l.trim().toLowerCase().startsWith("select")){
                return SQL_TYPE.select;
            }else if(l.trim().toLowerCase().startsWith("insert")){
                return SQL_TYPE.insert;
            }else if(l.trim().toLowerCase().startsWith("update")){
                return SQL_TYPE.update;
            }if(l.trim().toLowerCase().startsWith("delete")){
                return SQL_TYPE.delete;
            }
        }
        return SQL_TYPE.other;
    }

}
