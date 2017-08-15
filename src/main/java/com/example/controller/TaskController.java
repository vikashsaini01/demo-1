package com.example.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Task;
import com.example.ws.service.EmailService;
import com.example.ws.service.TaskService;

@RestController
public class TaskController extends BaseController{

	@Autowired
	TaskService taskService;
	
	@Autowired
	EmailService emailService;

	Logger logger = LoggerFactory.getLogger(getClass());


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/ws/task")
	public ResponseEntity<List<Task>> findAll() {

		List<Task> listTasks = taskService.findAll();
		
		ResponseEntity<List<Task>> responseEntity = new ResponseEntity<List<Task>>(listTasks, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/ws/task/{id}")
	public ResponseEntity<Task> findOne(@PathVariable long id) {
		logger.info("> findOne");
		Task taskFound = taskService.findOne(id);
		
		logger.info("< findOne");
		if (taskFound != null)
			return new ResponseEntity<Task>(taskFound, HttpStatus.OK);
		
		return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/ws/task")
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		if (task.getId() != 0) {
			// Can't create when id is specified
			return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Task taskSaved = taskService.addTask(task);
		if (taskSaved != null)
			return new ResponseEntity<Task>(taskSaved, HttpStatus.CREATED);
		return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/ws/task/{id}")
	public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable(value = "id") long id) {
		Task taskFound = taskService.findOne(task.getId());

		// Not persisted already, so can't be updated
		if (taskFound == null)
			return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);

		Task taskUpdated = taskService.updateTask(task);

		if (taskUpdated != null)
			return new ResponseEntity<Task>(taskUpdated, HttpStatus.OK);
		return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/ws/task/{id}")
	public ResponseEntity<Task> deleteTask(@RequestBody Task task, @PathVariable(value = "id") long id) {
		Task taskFound = taskService.findOne(task.getId());

		// Not persisted already, so can't be deleted
		if (taskFound == null)
			return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);

		taskService.deleteTask(taskFound);
		return new ResponseEntity<Task>(HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, 
			value = "/ws/task/{id}/send")
	public ResponseEntity<Task> sendTask(@PathVariable(value = "id") long id) {
		Task taskFound = taskService.findOne(id);
		if(taskFound== null){
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		Future<Boolean> asyncResult1 = emailService.sendAsyncWithResult(taskFound);
		Future<Boolean> asyncResult2 = emailService.sendAsyncWithResult(taskFound);
		Future<Boolean> asyncResult3 = emailService.sendAsyncWithResult(taskFound);
		logger.info("Before get in sendTask");
		try {
			logger.info("Result of asyncResult1 {}", asyncResult1.get());
			logger.info("Result of asyncResult2 {}", asyncResult2.get());
			logger.info("Result of asyncResult3 {}", asyncResult3.get());
		} catch (InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
		}
		logger.info("After get in sendTask");
		return new ResponseEntity<Task>(HttpStatus.OK);
	}

}
