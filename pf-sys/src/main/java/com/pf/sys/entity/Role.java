package com.moqu.manage.entity;

import com.dindon.core.utils.Common;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
	

	//columns START
	private String id;    //主键
	private String rolename;    //角色名称
	private Timestamp createtime;    //创建时间
	private String privileges;      //权限ids
	//columns END


	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getRolename() {
		return this.rolename;
	}
	
	public void setRolename(String value) {
		this.rolename = value;
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

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	
}

