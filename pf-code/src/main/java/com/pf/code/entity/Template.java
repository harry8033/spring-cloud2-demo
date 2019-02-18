
package com.pf.code.entity;


public class Template {
	

	//columns START
	private Integer id;    //
	private String name;    //模板名称
	private String filename;    //文件名
	private String filepath;    //文件路径
	private String files;    //压缩包内内容
	private String ctime;
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
	public String getFilename() {
		return this.filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return this.filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFiles() {
		return this.files;
	}
	
	public void setFiles(String files) {
		this.files = files;
	}
	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
}

