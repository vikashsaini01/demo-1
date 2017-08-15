package com.example.ws.actuator.health;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.example.entity.Task;
import com.example.ws.service.TaskService;

@Component
public class TaskHealthIndicator implements HealthIndicator{

	@Autowired
	TaskService taskService ;
	
	@Override
	public Health health() {
		Collection<Task> collection = taskService.findAll();
		if(collection==null || collection.size()==0 ){
			return Health.down().withDetail("Task count", collection.size()).build();			
		}
		return Health.up().withDetail("Task count", collection.size()).build();
	}
	

}
