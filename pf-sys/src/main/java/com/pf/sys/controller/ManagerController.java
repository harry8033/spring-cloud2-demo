package com.moqu.manage.controller;

import com.dindon.core.base.BaseController;
import com.dindon.core.utils.Common;
import com.dindon.core.utils.Page;
import com.moqu.manage.entity.Manager;
import com.moqu.manage.service.ManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/mgr/manager")
public class ManagerController extends BaseController{

	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/list")
	public String list(){
		return "views/system/manager/list";
	}
	
	/**分页查询*/
	@ResponseBody
	@RequestMapping("/findBy")
	public Page<Manager> findByPage() {
		Map<String, Object> params = getRequestParams();
        return managerService.findByPage(params);
    }

	/**
	 * 新增or更新
	 */
	@ResponseBody
	@RequestMapping("/saveEntity")
	public String saveEntity(@RequestBody Manager entity){
		if(!Common.isEmpty(entity.getId())){
			managerService.updateEntity(entity);
		}else{
			managerService.addEntity(entity);
		}
		return "success";
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/deleteEntity")
	public String deleteEntity(){
		String id = getParaValue("id");
		managerService.deleteByIds(new String[]{id});
		return "success";
	}
	
	private static final String algorithmName = "md5";
	private static final int hashIterations = 2;
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@ResponseBody
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
	}
}