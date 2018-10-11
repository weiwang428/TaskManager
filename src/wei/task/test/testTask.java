/**
 * 
 */
package wei.task.test;

import wei.task.*;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Wei Wang
 *
 */
public class testTask {
	Task t;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		t = new Task();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testSetTaskName() {		
		try {
			t.setTaskName(null);
			Assert.fail("Failure of testing setTaskNme");
		} catch (IllegalArgumentException e) {
			
		}
		try {
			t.setTaskName(" ");
			Assert.fail("Failure of testing setTaskNme");
		} catch (IllegalArgumentException e) {

		}	
	}
	
	@Test
	public void testGetTaskName() {
		try {
			t.getTaskName();
			Assert.fail("Failure of testing getTaskNme");
		} catch (NullPointerException e) {
			
		}
		t.setTaskName(" task1  ");
		Assert.assertTrue(t.getTaskName().equals("task1"));
	}
	
	@Test
	public void testSetProject() {
		try {
			t.setProject(null);
			Assert.fail("Failure of testing setProject");
		} catch (IllegalArgumentException e) {
			
		}
		try {
			t.setProject(" ");
			Assert.fail("Failure of testing setProject");
		} catch (IllegalArgumentException e) {

		}	
	}
	
	
	@Test
	public void testGetProject() {
		try {
			t.getProject();
			Assert.fail("Failure of testing getProject");
		} catch (NullPointerException e) {
			
		}
		t.setProject(" Project1  ");
		Assert.assertTrue(t.getProject().equals("Project1"));
	}

	@Test
	public void testStatus() {
		try {
			t.setStatus(null);
			Assert.fail("Failure of testing setStatus");
		} catch (IllegalArgumentException e) {
			
		}
		try {
			t.setStatus("");
			Assert.fail("Failure of testing setStatus");
		} catch (IllegalArgumentException e) {

		}
		try {
			t.setStatus("test");
			Assert.fail("Failure of testing setStatus");
		} catch (IllegalArgumentException e) {

		}
		t.setStatus("undo");
		Assert.assertTrue(t.getStatus().equals("undo"));		
	}
	
	@Test
	public void testSetDueTime() {
		try {
			t.setDueTime(null);
			Assert.fail("Failure of testing setDueTime");
		} catch (IllegalArgumentException e) {
			
		}
		try {
			t.setDueTime(t.getCreatTime().minusDays(1));
			Assert.fail("Failure of testing setDueTime");
		} catch (IllegalArgumentException e) {

		}
	}
	
	
	@Test
	public void testGetDueTime() {
		LocalDateTime time = LocalDateTime.now().plusDays(1);
		try {
			t.getDueTime();
			Assert.fail("Failure of testing getDueTime");
		} catch (NullPointerException e) {
			
		}
		t.setDueTime(time);
		Assert.assertTrue(t.getDueTime().equals(time));
	}
	
	@Test
	public void testSetTaskId() {
		thrown.expect(IllegalArgumentException.class);
		t.setTaskId(-3);
	}
	
	@Test
	public void testGetTaskId() {
		try {
			t.getTaskId();
			Assert.fail("Failure of testing getTaskId");
		} catch (NullPointerException e) {
			
		}
		t.setTaskId(6);
		Assert.assertTrue(t.getTaskId() == 6);
	}
	
}
