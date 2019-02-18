package com.pf.sys.controller;

import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.spring.base.BaseController;
import com.pf.spring.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pf.sys.service.PrivilegeService;
import com.pf.sys.entity.Tprivilege;

import java.util.Map;

@RestController
@RequestMapping("/sys/tprivilege")
public class TprivilegeController extends BaseController{

	@Autowired
	private PrivilegeService privilegeService;
	
	/**分页查询*/
	@RequestMapping(value = "/findBy", method = RequestMethod.GET)
	public Result findByPage() {
		Map<String, Object> params = getRequestParams();
		Page<Tprivilege> page = privilegeService.findByPage(params);
        return new Result().setData(page);
    }

	/**
	 * 新增or更新
	 */
	@ResponseBody
	@RequestMapping("/saveEntity", method = RequestMethod.POST)
	public Result saveEntity(@RequestBody Tprivilege entity){
		if(!Common.isEmpty(entity.getId())){
			privilegeService.updateEntity(entity);
		}else{
			privilegeService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete", method = RequestMethod.POST)
	public Result deleteEntity(){
		String id = getParaValue("id");
		privilegeService.deleteByIds(new String[]{id});
		return Result.asSuccess();
	}
}