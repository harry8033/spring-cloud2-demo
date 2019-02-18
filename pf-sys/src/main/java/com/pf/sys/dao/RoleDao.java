package com.moqu.manage.dao;

import com.moqu.manage.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2018/1/16.
 * Description:
 */
public interface RoleDao {

    List<Role> findBy(Map<String, Object> params);

    Role findById(String id);

    long getCount(Map<String, Object> params);

    void addEntity(Role obj);

    void updateEntity(Role obj);

    void deleteById(String id);

    void updateUserRoleRela(Map<String, Object> params);

    List<Role> findByManagerId(String managerid);

    void updateRolePrivRela(Map<String, Object> params);

}
