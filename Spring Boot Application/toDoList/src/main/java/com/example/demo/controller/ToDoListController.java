package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.service.TaskDetailsService;

@CrossOrigin
@RestController
public class ToDoListController {
	
	@Autowired
	TaskDetailsService service;
	
	@PostMapping("/loginRequest")
	public TaskResponse loginRequest(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password ) {
		return service.loginRequest(username,password); 
	}
	
	@GetMapping("/retrievetask")
	public TaskResponse getTask(@RequestParam(name = "name") String name ){
        System.out.println("Username!"+name);

		return service.getAllTaskDetails(name);		
	}
	
	@GetMapping("/taskbyid/{id}")
	public TaskResponse getTaskById(@PathVariable(name = "id") Long id ){
		return service.getTaskDetailsById(id);		
	}
	
	@PostMapping("/task")
	public TaskResponse saveTask(@RequestBody TaskRequest request) {
		request.setCreate(true);
		return service.saveOrUpdateTask(request); 
	}
	
	@PutMapping("/task")
	public TaskResponse updateTask(@RequestBody TaskRequest request) {
		request.setCreate(false);
		return service.saveOrUpdateTask(request); 
	}
	
	@DeleteMapping("/task")
	public TaskResponse deleteTaskById(@RequestParam(name = "id") Long id ){
		return service.deleteTask(id);		
	}

}
