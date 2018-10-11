package wei.task;

import java.io.FileNotFoundException;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

public class testTaskManager {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testGetTaskNum() {
		TaskManager manager = new TaskManager(); 		
		// Test before any adding.
		Assert.assertTrue(0 == manager.getDoneTaskNum());
		Assert.assertTrue(0 == manager.getUndoTaskNum());
		Task t1 = new Task();		
		Task t2 = new Task();
		t2.setStatus("done");
		Task t3 = new Task();		
		manager.addTask(t1);
		manager.addTask(t2);
		manager.addTask(t3);		
		// Check the counter after adding.
		Assert.assertTrue(1 == manager.getDoneTaskNum());
		Assert.assertTrue(2 == manager.getUndoTaskNum());		
	}
	
	@Test
	public void testAddTask() {
		TaskManager manager = new TaskManager(); 	
		Task t = new Task();
		manager.addTask(null);
		Assert.assertTrue(0 == manager.getUndoTaskNum());			
		manager.addTask(t);
		Task t1 = manager.getTaskById(0);
		// t and t1 should be the same
		Assert.assertTrue(t == t1);
	}
	
	@Test
	public void testGetTaskById() {
		TaskManager manager = new TaskManager(); 
		Task t = new Task();
		manager.addTask(t);		
		Task t1 = manager.getTaskById(1);
		Assert.assertNull(t1);
		t1 = manager.getTaskById(10);
		Assert.assertNull(t1);	
	}
	
	@Test
	public void testRemoveTask() {
		TaskManager manager = new TaskManager(); 
		Task t = new Task();
		manager.addTask(t);		
		manager.removeTask(10);
		Assert.assertTrue(1 == manager.getUndoTaskNum());	
		manager.removeTask(0);
		Assert.assertTrue(0 == manager.getUndoTaskNum());
	}
			
	@Test
	public void testLoadTaskListFromFile() {
		TaskManager manager = new TaskManager(); 
		try {
			manager.loadTaskListFromFile(null);
			Assert.fail("Failure of testing loadTaskListFromFile");
		} catch (FileNotFoundException e) {
			
		}
        catch (Exception e) {
			
		}
		try {
			Assert.assertTrue(manager.loadTaskListFromFile("taskList.bin"));
		} catch (Exception e) {
			Assert.fail("Failure of testing loadTaskListFromFile");
		}
	}
	
	@Test
	public void testSaveTaskListToFile() {
		TaskManager manager = new TaskManager(); 
		try {
			manager.SaveTaskListToFile(null);
			Assert.fail("Failure of testing SaveTaskListToFile");
		} 
        catch (Exception e) {
        	
		}
		try {
			Assert.assertTrue(manager.SaveTaskListToFile("taskList.bin"));
		} catch (Exception e) {
			Assert.fail("Failure of testing SaveTaskListToFile");
		}
	}
	
	
}
