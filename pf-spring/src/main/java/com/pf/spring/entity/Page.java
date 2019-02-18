package com.dindon.core.utils;

import java.util.List;
import java.util.Map;

public class Page<T>{

	//当前页
	private int number = 1;
	
	//每页大小
	private int size = 10;
	
	//内容
	private List<T> rows;
	
	//总条数
	private long total = 0;
	
	//开始位置
	private long start = 0l;
	
	//结束位置
	private long end = 0l;
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}

	public int getNumberOfElements() {
		if(rows != null)
			return rows.size();
		return 0;
	}

	public List<T> getRows() {
		return rows;
	}
	
	public void setRows(List<T> content){
		this.rows = content;
	}

	public boolean hasContent() {
		if(rows != null)
			return true;
		return false;
	}

	public boolean isFirst() {
		
		return number == 1;
	}

	public boolean isLast() {
		return number == getTotalPages();
	}

	public boolean hasNext() {
		return number < getTotalPages();
	}

	public boolean hasPrevious() {
		return number > 1;
	}

	public int getTotalPages() {
		int page = total==0||size==0?0:(int) (total / size);
		int ys = total==0||size==0?0:(int)(total % size);
		return page <1 ? page + 1 : ys > 0 ? page + 1 : page;
	}

	public void setTotal(long total){
		this.total = total;
	}
	
	public long getTotal(){
		return total;
	}

	public long getStart() {
		start = (number - 1) * size;
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		end = start + size;
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
	public Page(){
		this.number = 1;
		this.size = 10;
	}
	
	public Page(int pageNo, int pageSize){
		this.number = pageNo;
		this.size = pageSize;
	}
	
	public Page(Map<String, Object> params){
		if(params != null){
			try{
				start = Integer.parseInt(String.valueOf(params.get("offset")));
			}catch(NumberFormatException ne){
				
			}
			try{
				size = Integer.parseInt(String.valueOf(params.get("limit")));
			}catch(NumberFormatException ne){
				
			}
			number = start==0||size==0?0:(int)start/size + 1;
			params.put("pageStart", start);
			params.put("pageSize", getSize());
			params.put("pageEnd", getEnd());
		}
	}

}
