package com.example.demo.service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;

public interface TaskDetailsService {
	
	public TaskResponse getAllTaskDetails(String user);
	
	public TaskResponse getTaskDetailsById(long id);
	
	public TaskResponse saveOrUpdateTask(TaskRequest task);
	
	public TaskResponse deleteTask(long id);

	public TaskResponse loginRequest(String username, String password);

}
