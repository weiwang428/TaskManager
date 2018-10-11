package wei.task.test;

import wei.task.*;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

public class testTaskManager {
	TaskManager manager;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manager = new TaskManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetTaskNum() {
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
		Task t = new Task();
		Assert.assertFalse(manager.addTask(null));
		Assert.assertTrue(manager.addTask(t));
		Assert.assertEquals(1, manager.size());
		Task t1 = manager.getTaskById(0);
		// t and t1 should be the same
		Assert.assertTrue(t == t1);
	}

	@Test
	public void testGetTaskById() {
		Task t = new Task();
		manager.addTask(t);
		Assert.assertSame(t, manager.getTaskById(0));
		Assert.assertNull(manager.getTaskById(1));

		Task t1 = new Task();
		manager.addTask(t1);
		Assert.assertSame(t1, manager.getTaskById(1));
		Assert.assertNull(manager.getTaskById(2));
	}

	@Test
	public void testRemoveTask() {
		Task t = new Task();
		manager.addTask(t);
		Assert.assertFalse(manager.removeTask(10));
		Assert.assertTrue(manager.removeTask(0));
	}

	@Test
	public void testLoadSave() {
		String file_name = "test_task_manager.bin";

		// Create new Task add to the list and then save.
		Task t = new Task();
		Assert.assertTrue(manager.addTask(t));
		try {
			Assert.assertTrue(manager.SaveTaskListToFile(file_name));
		} catch (IOException e1) {
			Assert.fail("Failure of saving task list to the file test");
		}

		// test of loading from null.
		try {
			Assert.assertFalse(manager.loadTaskListFromFile(null));
		} catch (Exception e) {
			Assert.fail("Failure of load from null string test.");
		}

		// test of correct loading from file.
		try {
			Assert.assertTrue(manager.loadTaskListFromFile(file_name));
		} catch (Exception e) {
			Assert.fail("Failure of testing loadTaskListFromFile");
		}
		
		// check if the object loading is correct or not.
		Assert.assertNotNull(manager.getTaskById(0));
	}
}
