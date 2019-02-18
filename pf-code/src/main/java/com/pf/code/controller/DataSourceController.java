package com.pf.code.controller;

import com.pf.code.entity.DataSource;
import com.pf.code.service.DataSourceService;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import com.pf.spring.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/code/datasource")
public class DataSourceController extends BaseController{

	@Autowired
	private DataSourceService dataSourceService;
	
	/**分页查询*/
	@RequestMapping(value = "/findBy", method = RequestMethod.GET)
	public Result findByPage() {
		Map<String, Object> params = getRequestParams();
		Page<DataSource> page = dataSourceService.findByPage(params);
        return new Result().setData(page);
    }

	/**
	 * 新增or更新
	 */
	@ResponseBody
	@RequestMapping(value = "/saveEntity", method = RequestMethod.POST)
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
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result deleteEntity(){
		String id = getParaValue("id");
		dataSourceService.deleteByIds(new String[]{id});
		return Result.asSuccess();
	}
}