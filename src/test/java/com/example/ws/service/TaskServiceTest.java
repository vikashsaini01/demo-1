package com.example.ws.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.AbstractTest;
import com.example.entity.Task;
@Transactional
public class TaskServiceTest extends AbstractTest {

	@Autowired
	private TaskService taskService;
	
	@BeforeClass
	public static void setUpClass(){
				
	}
	
	
	@Before
	public void setUp(){
		taskService.evictCache();		
	}
	
	@After
	public void tearDown(){}
	
	@Test
	public void testFindAll(){
		Collection<Task> collection = taskService.findAll();
		assertNotNull("failure - expected not null", collection);
		assertTrue("failure - size must be one or more", collection.size()>=1);
	}
	
	@Test
	public void testFindOne(){
		/*long id = 1;
		Task taskFound = taskService.findOne(id);
		assertNotNull("failure - expected not null", taskFound);
		Assert.assertEquals("failure - Id must match", id, taskFound.getId());*/
	}
	
	@Test
	public void testFindOneNotFound(){
		long id = Long.MAX_VALUE;
		Task taskFound = taskService.findOne(id);
		Assert.assertNull("failure - expected null", taskFound);
	}
	
	@Test
	public void testCreate(){
		Task task = new Task();
		task.setTaskShort("test Task ");
		task.setTaskDetail("detail task");
		Task taskCreated = taskService.addTask(task);
		Assert.assertNotNull("failure - expected not null", taskCreated);
		Assert.assertNotNull("ID should not be null",taskCreated.getId());
		Assert.assertEquals("task short should match",task.getTaskShort(), taskCreated.getTaskShort());
		Assert.assertEquals("task details should match", task.getTaskDetail(), taskCreated.getTaskDetail());
		
		Collection<Task> collection = taskService.findAll();
		Assert.assertTrue("Created task should be returned by findAll", collection.contains(task));
		Assert.assertNotNull("Created task should be returned by findOne", taskService.findOne(taskCreated.getId()));
	}
	
	
	@Test
	public void testUpdate(){
		Task task = new Task();
		task.setTaskShort("test Task to Update");
		task.setTaskDetail("detail task to Update");
		Task taskCreated = taskService.addTask(task);
		
		//update the created one
		Task taskFound = taskService.findOne(taskCreated.getId());
		taskFound.setTaskDetail("Updated task details");
		taskFound.setTaskDetail("Updated task short");
		Task taskUpdated= taskService.updateTask(taskFound);
		
		Assert.assertNotNull("failure - expected not null", taskUpdated);
		Assert.assertEquals("ID should be same", taskCreated.getId(),taskUpdated.getId());
		Assert.assertEquals("task short should match",taskCreated.getTaskShort(), taskUpdated.getTaskShort());
		Assert.assertEquals("task details should match", taskCreated.getTaskDetail(), taskUpdated.getTaskDetail());
		
		Collection<Task> collection = taskService.findAll();
		Assert.assertTrue("Updated task should be returned by findAll", collection.contains(taskUpdated));
		Assert.assertNotNull("Updated task should be returned by findOne", taskService.findOne(taskUpdated.getId()));
	}
	
	@Test
	public void testDelete(){
		Task task = new Task();
		task.setTaskShort("test Task to delete");
		task.setTaskDetail("detail task to delete");
		Task taskCreated = taskService.addTask(task);
		
		taskService.deleteTask(taskCreated);
		Assert.assertNull("failure - expected null", taskCreated);
		Assert.assertFalse("Deleted one shouldnt be returned by findall", taskService.findAll().contains(taskCreated));
	}
}
