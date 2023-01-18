package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long>  {

}
