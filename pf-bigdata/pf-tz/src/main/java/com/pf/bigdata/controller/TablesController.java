package com.pf.bigdata.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.bigdata.entity.ImportTable;
import com.pf.bigdata.entity.Tables;
import com.pf.bigdata.service.TablesService;
import com.pf.code.service.DataSourceService;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Ru He
 * Date: Created in 2019/2/1.
 * Description: Tables控制器
 */
@RestController
@RequestMapping("/bigdata/tables")
public class TablesController extends BaseController{

	@Autowired
	private TablesService tablesService;
	@Autowired
	private DataSourceService dataSourceService;
	
	/**
	 * 功能描述: 分页查询数据
	 * @auther Ru He
	 * @param param 查询参数
	 * @return 分页数据
	 * @date 2019/2/1
	 */
	@RequestMapping(value = "/findBy", method = RequestMethod.POST)
	public Result findByPage(@RequestBody Param param) {
		PageHelper.startPage(param.getIntValue("page"),
				param.getIntValue("size"));
		PageInfo<Tables> page = new PageInfo<>(tablesService.findByPage(param));
        return new Result().setData(page);
    }

	/**
	 * 功能描述: 新增或更新实例到数据库
	 * @auther Ru He
	 * @param entity 实例
	 * @return 处理结果
	 * @date 2019/2/1
	 */
	@ResponseBody
	@RequestMapping(value = "/saveEntity", method = RequestMethod.POST)
	public Result saveEntity(@RequestBody Tables entity){
		if(!Common.isEmpty(entity.getId())){
			tablesService.updateEntity(entity);
		}else{
			entity.setId(Common.getUUID());
			tablesService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 功能描述: 根据主键删除数据
	 * @auther Ru He
	 * @return 处理结果
	 * @date 2019/2/1
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result deleteEntity(){
		String id = getParaValue("id");
		tablesService.deleteByIds(new String[]{id});
		return Result.asSuccess();
	}

	/**
	 * 功能描述:
	 * @auther Ru He
	 * @param id 数据源ID
	 * @return 返回数据源下所有表的信息（包括字段）
	 * @date 2019/2/1 下午3:36
	 */
	@GetMapping(value = "/getTables/{id}")
	public Result viewTables(@PathVariable("id") Integer id){
		List list = tablesService.getTables(id);
		return Result.asSuccess().setData(list);
	}

	/**
	 * 功能描述: 获取所有的数据源
	 * @auther Ru He
	 * @return 数据源列表
	 * @date 2019/2/1 下午2:43
	 */
	@GetMapping(value = "/getDataSources")
	public Result getDataSources(){
		return Result.asSuccess().setData(tablesService.getDataSources());
	}

	/**
	 * 功能描述: 导入指定的表格
	 * @auther Ru He
	 * @param table 要导入的表信息
	 * @return 返回处理结果
	 * @date 2019/2/1 下午4:07
	 */
	@PostMapping(value = "/importTable")
	public Result importTable(@RequestBody ImportTable table){
		tablesService.importTable(table);
		return Result.asSuccess();
	}
}