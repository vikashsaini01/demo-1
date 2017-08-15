package com.example.ws.service;

import java.util.List;

import com.example.entity.Task;

public interface TaskService {
	public Task addTask(Task task);
	public Task updateTask(Task task);
	public List<Task> findAll();
	public Task findOne(long id);
	public void deleteTask(Task task);
	public void evictCache();
}
