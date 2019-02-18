package com.pf.code.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.code.entity.DataSource;
import com.pf.code.service.DataSourceService;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code/datasource")
public class DataSourceController extends BaseController{

	@Autowired
	private DataSourceService dataSourceService;
	
	/**分页查询*/
	@PostMapping(value = "/findBy")
	public Result findByPage(@RequestBody Param params) {
		PageHelper.startPage(params.getIntValue("page"),
				params.getIntValue("size"));
		PageInfo<DataSource> page = new PageInfo<>(dataSourceService.findBy(params));
        return new Result().setData(page);
    }

	/**
	 * 新增or更新
	 */
	@PostMapping(value = "/saveEntity")
	public Result saveEntity(@RequestBody DataSource entity){
		if(!Common.isEmpty(entity.getId())){
			dataSourceService.updateEntity(entity);
		}else{
			dataSourceService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 删除
	 */
	@PostMapping(value = "/delete")
	public Result deleteEntity(@RequestBody Param param){
		dataSourceService.deleteByIds(param.getList("ids", String.class));
		return Result.asSuccess();
	}

}