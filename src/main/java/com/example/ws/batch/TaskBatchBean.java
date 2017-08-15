package com.example.ws.batch;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.entity.Task;
import com.example.ws.service.TaskService;

@Component
@Profile(value="batch")
public class TaskBatchBean {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TaskService taskService;
	
	@Scheduled(fixedDelayString="${batch.task.fixeddelay}", initialDelayString="${batch.task.initialdelay}")
	public void cronJob(){
		Collection<Task> collection = taskService.findAll();
		logger.info("There are {} tasks in database, as - {}", collection.size(), collection);		
	}
}
