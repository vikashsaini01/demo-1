package com.example.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Task;
import com.example.ws.repository.TaskRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TaskServiceBean implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "tasks", key = "#result.id")
	public Task addTask(Task task)  {
		if(task.getTaskShort()==null || task.getTaskShort().isEmpty())
			throw new RuntimeException("Task short cant be empty");
		Task taskInserted = taskRepository.save(task);
		if (taskInserted.getId() == 3L)
			throw new RuntimeException("ID 3 not allowed, unlucky!!");
		return taskInserted;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "tasks", key = "#task.id")
	public Task updateTask(Task task){
		if(task.getTaskShort()==null || task.getTaskShort().isEmpty())
			throw new RuntimeException("Task short cant be empty");
		Task taskUpdated = taskRepository.save(task);
		return taskUpdated;
	}

	void someData() {
		Task k = new Task();
		k.setTaskShort("Be joyful");
		k.setTaskDetail("Being joyfull should be your default state!");
		taskRepository.save(k);
	}

	@Override
	public List<Task> findAll() {
		List<Task> listTasks = taskRepository.findAll();
		
		if (listTasks == null || listTasks.size() == 0) {
			someData();
			listTasks = taskRepository.findAll();
		}
		return listTasks;
	}

	@Override
	@Cacheable(value = "tasks", key = "#id")
	public Task findOne(long id) {
		Task taskFound = taskRepository.findOne(id);
		return taskFound;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "tasks", key = "#task.id")
	public void deleteTask(Task task) {
		taskRepository.delete(task.getId());
	}

	@Override
	@CacheEvict(value = "tasks", allEntries = true)
	public void evictCache() {

	}
}
