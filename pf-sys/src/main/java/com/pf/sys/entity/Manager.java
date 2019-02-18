package com.pf.sys.entity;

import com.pf.core.util.Common;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Manager {
	

	//columns START
	private String id;    //主键
	private String account;    //账号
	private String pwd;    //密码
	private Integer state;    //状态：0、禁用；1、启用
	private Timestamp createtime;    //创建时间
	private List<Role> roleList;
	private String roles;
	private String salt;
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getAccount() {
		return this.account;
	}
	
	public void setAccount(String value) {
		this.account = value;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String value) {
		this.pwd = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	
	public void setState(Integer value) {
		this.state = value;
	}
	
	public String getCreatetimeString() {
		return Common.dateToString(getCreatetime());
	}
	
	public Timestamp getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Timestamp value) {
		this.createtime = value;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}

