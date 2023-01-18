package com.example.demo.dto;


import java.util.List;

import com.example.demo.model.TaskModel;

public class TaskResponse {
	
	private String status;
	
	private boolean success;
	
	private String reason;
	
	private List<TaskModel> allTaskList;
	
	private TaskModel task;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public List<TaskModel> getAllTaskList() {
		return allTaskList;
	}

	public void setAllTaskList(List<TaskModel> allTaskList) {
		this.allTaskList = allTaskList;
	}

	public TaskModel getTask() {
		return task;
	}

	public void setTask(TaskModel task) {
		this.task = task;
	}
	
	

}

