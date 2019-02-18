
package com.pf.sys.entity;

/**
 * Author: 作者
 * Date: Created in yyyy/mm/dd.
 * Description: 实体类
 */
public class OptLog {
	

	/*主键*/
	private Integer id;
	/*模块名称*/
	private String module;
	/*操作类型*/
	private String opt;
	/*操作人*/
	private String oper;
	/*操作状态：success、excption*/
	private String state;
	private String ctime;


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getModule() {
		return this.module;
	}
	
	public void setModule(String module) {
		this.module = module;
	}
	public String getOpt() {
		return this.opt;
	}
	
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getOper() {
		return this.oper;
	}
	
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getState() {
		return this.state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
}

