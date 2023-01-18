package com.example.demo.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.model.TaskModel;
import com.example.demo.service.TaskDetailsService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ToDoListController.class)
class ToDoListControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskDetailsService service;
	
	
	@Test
	public void retrieveTaskDetails() throws Exception {
		
		String expResponse = "{\"status\":\"200\",\"success\":true,\"reason\":null,\"allTaskList\":[{\"id\":1,\"name\":\"user1\",\"completed\":false,\"taskName\":null,\"taskDetails\":null,\"taskCreatedDate\":null,\"taskUpdatedDate\":null}],\"task\":null}";
		
		TaskModel mockData= new TaskModel();
		mockData.setId(1l);
		mockData.setCompleted(false);
		mockData.setName("user1");
		List<TaskModel>dataList = Arrays.asList(mockData);
		TaskResponse res =new TaskResponse();
		res.setStatus("200");
		res.setSuccess(true);
		res.setAllTaskList(dataList);
		
		Mockito.when(service.getAllTaskDetails("user1"))
				.thenReturn(res);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/retrievetask?name=user1").accept(
				MediaType.APPLICATION_JSON);
		

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				
		JSONAssert.assertEquals(expResponse, result.getResponse()
				.getContentAsString(), false);
		
	}
	
	@Test
	public void retrieveTaskDetailsEmpty() throws Exception {
		
		String expResponse = "{\"status\":\"200\",\"success\":true,\"reason\":null,\"allTaskList\":[],\"task\":null}\r\n"
				+ "";
		
	
		List<TaskModel>dataList = new ArrayList();
		TaskResponse res =new TaskResponse();
		res.setStatus("200");
		res.setSuccess(true);
		res.setAllTaskList(dataList);
		
		Mockito.when(service.getAllTaskDetails("user2"))
				.thenReturn(res);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/retrievetask?name=user2").accept(
				MediaType.APPLICATION_JSON);
		

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expResponse, result.getResponse()
				.getContentAsString(), false);
		
	}

	@Test
	public void postTaskDetails() throws Exception {

		String user1 = "{\r\n" + "    \"userName\": \"udhaya\",\r\n" + "    \"taskName\": \"task1\",\r\n"
				+ "    \"taskDetails\": \"task1\",\r\n" + "    \"completed\": false,\r\n" + "    \"id\": 1\r\n" + "}";
		TaskRequest mockreq = new TaskRequest();
		mockreq.setId(1L);
		mockreq.setName("user1");
		mockreq.setTaskName("task1");
		mockreq.setCompleted(false);
		mockreq.setTaskDetails("task1");

		TaskModel mockData = new TaskModel();
		mockData.setId(1L);
		mockData.setName("user1");
		mockData.setTaskName("task1");
		mockData.setCompleted(false);
		List<TaskModel> dataList = Arrays.asList(mockData);

		TaskResponse res = new TaskResponse();
		res.setStatus("200");
		res.setSuccess(true);
		res.setTask(mockData);

		Mockito.when(service.saveOrUpdateTask(Mockito.any(TaskRequest.class))).thenReturn(res);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/task").accept(MediaType.APPLICATION_JSON)
				.content(user1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void putTaskDetails() throws Exception {
		String user1 = "{\r\n" + "    \"userName\": \"udhaya\",\r\n" + "    \"taskName\": \"task1\",\r\n"
				+ "    \"taskDetails\": \"task1 - to do the login page\",\r\n" + "    \"completed\": true,\r\n"
				+ "    \"id\": 1\r\n" + "}";
		TaskRequest mockreq = new TaskRequest();
		mockreq.setId(1L);
		mockreq.setName("user1");
		mockreq.setTaskName("task1");
		mockreq.setCompleted(true);
		mockreq.setTaskDetails("task1 - to do the login page");

		TaskModel mockData = new TaskModel();
		mockData.setId(1L);
		mockData.setName("user1");
		mockData.setTaskName("task1");
		mockData.setCompleted(true);
		mockData.setTaskDetails("task1 - to do the login page");

		TaskResponse res = new TaskResponse();
		res.setStatus("200");
		res.setSuccess(true);
		res.setTask(mockData);

		Mockito.when(service.saveOrUpdateTask(Mockito.any(TaskRequest.class))).thenReturn(res);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/task").accept(MediaType.APPLICATION_JSON)
				.content(user1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

}
