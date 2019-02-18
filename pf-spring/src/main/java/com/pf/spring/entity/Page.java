package com.pf.spring.entity;

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
	private long total = 0L;
	
	//开始位置
	private long start = 0L;
	
	//结束位置
	private long end = 0L;
	
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
		if(rows != null){
			return rows.size();
		}
		return 0;
	}

	public List<T> getRows() {
		return rows;
	}
	
	public void setRows(List<T> content){
		this.rows = content;
	}

	public boolean hasContent() {
		if(rows != null){
			return true;
		}
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
	}

	public Page(int page){
		start = (page - 1) * size;

	}
	
	public Page(int pageNo, int pageSize){
		this.number = pageNo;
		this.size = pageSize;
	}
	
	public Page(Map<String, Object> params){
		if(params != null){
			/*判断参数中是否包含page参数*/
			if(params.containsKey("page")){
				try{
					this.number = Integer.parseInt(String.valueOf(params.get("page")));
				}catch(NumberFormatException ne){

				}
				this.start = (this.number - 1) * this.size;
			}else{
				try{
					this.start = Integer.parseInt(String.valueOf(params.get("offset")));
				}catch(NumberFormatException ne){

				}
				try{
					this.size = Integer.parseInt(String.valueOf(params.get("limit")));
				}catch(NumberFormatException ne){

				}
				this.number = this.start==0 || this.size == 0 ? 0 : (int)this.start / this.size + 1;
			}
			params.put("pageStart", this.start);
			params.put("pageSize", getSize());
			params.put("pageEnd", getEnd());
		}
	}

}
