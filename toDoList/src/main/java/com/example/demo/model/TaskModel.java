package com.example.demo.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="usertask",uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME", "TASK_NAME" }) })
public class TaskModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "USER_NAME", length = 50, nullable = false, unique = false)
	private String name;
	
	@Column(name = "IS_DONE",  nullable = false, unique = false)
	private boolean completed;
	
	@Column(name = "TASK_NAME", nullable = false, unique = false)
	private String taskName;
	
	
	@Column(name = "TASK_DETAILS", nullable = true, unique = false)
	private String taskDetails;
	
	
	@Column(name = "TASK_CREATED_DATE", nullable = true, unique = false)
	private LocalDateTime taskCreatedDate;
	
	@Column(name = "TASK_UPDATED_DATE", nullable = true, unique = false)
	private LocalDateTime taskUpdatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public LocalDateTime getTaskCreatedDate() {
		return taskCreatedDate;
	}

	

	public LocalDateTime getTaskUpdatedDate() {
		return taskUpdatedDate;
	}

	public void setTaskUpdatedDate(LocalDateTime taskUpdatedDate) {
		this.taskUpdatedDate = taskUpdatedDate;
	}

	public void setTaskCreatedDate(LocalDateTime taskCreatedDate) {
		this.taskCreatedDate = taskCreatedDate;
	}
	

}

