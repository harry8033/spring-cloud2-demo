package com.pf.sys.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.dindon.core.utils.Page;
import com.pf.sys.entity.Tprivilege;
import com.pf.sys.dao.TprivilegeDao;

@Service
public class TprivilegeService{

	@Autowired
	private TprivilegeDao tprivilegeDao;
	
	/**条件查询*/
	public List<Tprivilege> findBy(Map<String, Object> params) {
        return tprivilegeDao.findBy(params);
    }
	
	/**根据主键查询*/
	public Tprivilege findById(String id) {
        return tprivilegeDao.findById(id);
    }
	
	/**分页查询*/
	public Page<Tprivilege> findByPage(Map<String, Object> params) {
    	Page<Tprivilege> page = new Page<Tprivilege>(params);
		page.setRows(tprivilegeDao.findBy(params));
		page.setTotal(tprivilegeDao.getCount(params));
        return page;
    }
	
	/**
	 * 新增
	 */
	public void addEntity(Tprivilege tprivilege){
		tprivilegeDao.addEntity(tprivilege);
	}
	
	/**
	 * 修改
	 */
	public void updateEntity(Tprivilege tprivilege){
		tprivilegeDao.updateEntity(tprivilege);
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void deleteByIds(String[] ids){
		if(ids != null){
			for(String id : ids){
				tprivilegeDao.deleteById(id);
			}
		}
	}
}
