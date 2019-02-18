package com.pf.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
	

	//columns START
	private String id;    //主键
	private String rolename;    //角色名称
	private String ctime;    //创建时间
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
	
	public String getCtime() {
		return this.ctime;
	}
	
	public void setCtime(String value) {
		this.ctime = value;
	}

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	
}

