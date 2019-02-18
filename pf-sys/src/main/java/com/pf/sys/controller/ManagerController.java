package com.pf.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pf.core.entity.Param;
import com.pf.core.entity.Result;
import com.pf.core.util.Common;
import com.pf.core.util.Md5Utils;
import com.pf.shiro.util.PasswordHelper;
import com.pf.spring.base.BaseController;
import com.pf.sys.aspectj.annotation.ModuleLog;
import com.pf.sys.aspectj.enums.OptType;
import com.pf.sys.entity.Manager;
import com.pf.sys.entity.Menu;
import com.pf.sys.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/manager")
public class ManagerController extends BaseController{

	@Autowired
	private ManagerService managerService;
	
	/**分页查询*/
	@PostMapping(value = "/findBy")
	public Result findByPage(@RequestBody Param param) {
        PageHelper.startPage(param.getIntValue("page"),
                param.getIntValue("size"));
		PageInfo<Manager> page = new PageInfo<>(managerService.findBy(param));
        return Result.asSuccess().setData(page);
    }

	/**
	 * 新增or更新
	 */
	@PostMapping(value = "/saveEntity")
	public Result saveEntity(@RequestBody Manager entity){
		if(!Common.isEmpty(entity.getId())){
			managerService.updateEntity(entity);
		}else{
			managerService.addEntity(entity);
		}
		return Result.asSuccess();
	}

	/**
	 * 重置用户密码
	 */
	@PostMapping(value = "/reset")
	@ModuleLog(module = "管理员管理", type = OptType.RESET)
	public Result reset(@RequestBody Param param){
		String[] arr = PasswordHelper.encryptPassword(Md5Utils.getMD5("000000".getBytes()),
				param.getString("account"));
		param.put("newpwd", arr[0]);
		param.put("salt", arr[1]);
		managerService.updatePwd(param);
		return Result.asSuccess();
	}

	/**
	 * 删除
	 */
	@PostMapping(value = "/deleteEntity")
	@ModuleLog(module = "管理员管理", type = OptType.DELETE)
	public Result deleteEntity(@RequestBody Param param){
		managerService.deleteByIds(param.getList("ids", String.class));
		return Result.asSuccess();
	}
	
	/**
	 * 修改密码
	 */
	/*@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public String changePwd(HttpServletRequest request){
		Map<String, String> params = getRequestParamsStr();
		Subject subject = SecurityUtils.getSubject();
		Session s = subject.getSession();
		Manager u = (Manager)s.getAttribute("userSession");
		String result = "FAIL";
		
		String match = new SimpleHash(algorithmName, params.get("cryoldpwd"), ByteSource.Util.bytes(u.getAccount() + u.getSalt()), hashIterations).toHex();
		if(match.equals(u.getPwd())){
			params.put("account", u.getAccount());
			String newPassword = new SimpleHash(algorithmName, params.get("crynewpwd"), ByteSource.Util.bytes(u.getAccount() + u.getSalt()), hashIterations).toHex();
			params.put("newpwd", newPassword);
			managerService.updatePwd(params);
			result = "SUCCESS";
		}
		return result;
	}*/

	@GetMapping("/getMenus")
	public Result getMenus(){
		//List<Menu> menus = managerService.getMenus(SessionUtil.getUserId());
		List<Menu> menus = managerService.getMenus("e628528451344cf1845e0dd34226cd38");
		return Result.asSuccess().setData(menus);
	}
}