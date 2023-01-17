package com.example.demo.service.impl;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.TaskRepository;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.model.TaskModel;
import com.example.demo.service.TaskDetailsService;

@Service
public class TaskDetailsServiceImpl implements TaskDetailsService {
	
	@Autowired
	TaskRepository taskRepo;


	@Override
	public TaskResponse loginRequest(String username, String password) {
		TaskResponse response = new TaskResponse();

		HashMap< String,String> userMap=new HashMap<String,String>();
		userMap.put("user1", "password");
		userMap.put("user2", "password");
		userMap.put("user3", "password");
		
		if (userMap.containsKey(username)) {
            if(userMap.get(username).toString().equals(password)) {
                System.out.println("Login Successful");
    			response.setStatus("200");
    			response.setSuccess(true);
            }
        } else {
            System.out.println("Invalid Username!");
			response.setStatus("401");
			response.setSuccess(false);
        }
		
		return response;
	}
	
	@Override
	public TaskResponse getAllTaskDetails(String user) {
		TaskResponse response = new TaskResponse();
//		TaskModel task = new TaskModel();
//		task.setName(user);
		try {
			response.setStatus("200");
			response.setSuccess(true);
//			response.setAllTaskList(taskRepo.findAll(Example.of(task)));
			List<TaskModel> UserTaskList = taskRepo.findAll().stream().filter(i->i.getName().equals(user)).collect(Collectors.toList());
			response.setAllTaskList(UserTaskList);
		} catch (Exception e) {
			response.setStatus("500");
			response.setSuccess(false);
			response.setReason(e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public TaskResponse getTaskDetailsById(long id) {
		TaskResponse response = new TaskResponse();
		
		try {
			Optional<TaskModel> taskdetails = taskRepo.findById(id);
			response.setStatus("200");
			response.setSuccess(true);
			if(taskdetails.isPresent()) {
			response.setTask(taskdetails.get());	
			}else {
				response.setSuccess(false);
			}
		} catch (Exception e) {
			response.setStatus("500");
			response.setSuccess(false);
			response.setReason(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public TaskResponse saveOrUpdateTask(TaskRequest task) {
		TaskModel model = new TaskModel();
		TaskResponse response = new TaskResponse();

	try {
		model.setCompleted(task.isCompleted());
		model.setName(task.getName());		
		model.setTaskDetails(task.getTaskDetails());
		model.setTaskName(task.getTaskName());
		if(task.isCreate()) {	
			model.setTaskCreatedDate(LocalDateTime.now());			
		}else {
			Optional<TaskModel> taskdetails = taskRepo.findById(task.getId());
			if(taskdetails.isPresent()) {
			model.setTaskCreatedDate(taskdetails.get().getTaskCreatedDate());
			model.setTaskUpdatedDate(LocalDateTime.now());
			model.setId(task.getId());
			}else {
				throw new Exception("Task not found");
			}
		}
		
		
		TaskModel taskdetails = taskRepo.save(model);
		
		response.setStatus("200");
		response.setSuccess(true);
		response.setTask(taskdetails);
		List<TaskModel> UserTaskList = taskRepo.findAll().stream().filter(i->i.getName().equals(task.getName())).collect(Collectors.toList());

		response.setAllTaskList(UserTaskList);
		
	}catch (DataIntegrityViolationException e) {
		response.setStatus("500");
		response.setSuccess(false);
		response.setReason("Task Already exits");
	}catch (Exception e) {
		response.setStatus("500");
		response.setSuccess(false);
		response.setReason(e.getMessage());
		e.printStackTrace();
	}	
		return response;
	}

	@Override
	public TaskResponse deleteTask(long id) {
		TaskResponse response = new TaskResponse();

		try {
			taskRepo.deleteById(id);
			response.setStatus("200");
			response.setSuccess(true);
		} catch (Exception e) {
			response.setStatus("500");
			response.setSuccess(false);
			response.setReason(e.getMessage());
			e.printStackTrace();
		}
		
		
		return response;
	}


}
