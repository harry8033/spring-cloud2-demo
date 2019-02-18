package com.pf.sys.entity;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Privilege{
	
	private List<Menu> children;
	
	public Menu(){
		children = new ArrayList<>();
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String[] getFileList(){
		if(!StringUtils.isEmpty(this.getFiles())){
			return this.getFiles().trim().split("\n");
		}
		this.setFiles(null);
		return null;
	}
	
}
