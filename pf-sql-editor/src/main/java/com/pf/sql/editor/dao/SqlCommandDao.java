package com.pf.sql.editor.dao;

import java.util.List;
import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2019/1/30.
 * Description:
 */
public interface SqlCommandDao {

    List<Map<String, Object>> queryStatement(Map<String, Object> params);

    long queryCount(Map<String, Object> params);

    int updateStatement(String sql);
}
