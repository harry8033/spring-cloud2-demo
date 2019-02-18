
package com.pf.schedule.entity;

/**
 * Author: Ru He
 * Date: Created in 2019/2/3.
 * Description: 实体类
 */
public class Task {

	private String schedName;
	private String jobName;
	private String jobGroup;
	private String groupName;
	private String description;
	private String jobClassName;
	private String cron;
	private String state;


	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJobClassName() {
		return this.jobClassName;
	}
	
	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

