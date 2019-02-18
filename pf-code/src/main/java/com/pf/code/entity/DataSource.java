
package com.pf.code.entity;


public class DataSource {
	

	//columns START
	private Integer id;    //
	private String name;    //数据源名称
	private String url;    //连接字符串
	private String uname;    //用户名
	private String upass;    //密码
	private String dbtype;    //数据源类型
	private String driverclass;    //驱动类
	//columns END


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUname() {
		return this.uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return this.upass;
	}
	
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getDbtype() {
		return this.dbtype;
	}
	
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public String getDriverclass() {
		return this.driverclass;
	}
	
	public void setDriverclass(String driverclass) {
		this.driverclass = driverclass;
	}
}

