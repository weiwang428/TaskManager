/**
 * 
 */
package wei.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		Task t = new Task();
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
		Task t = new Task();
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
		Task t = new Task();
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
		Task t = new Task();
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
		Task t = new Task();
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
		Task t = new Task();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			t.setDueTime(null);
			Assert.fail("Failure of testing setDueTime");
		} catch (IllegalArgumentException e) {
			
		}
		try {
			t.setDueTime(" ");
			Assert.fail("Failure of testing setDueTime");
		} catch (IllegalArgumentException e) {

		}	
		try {
			t.setDueTime("2018-11-1111");
			Assert.fail("Failure of testing setDueTime");
		} catch (IllegalArgumentException e) {

		}
		try {
			t.setDueTime(t.getCreatTime().minusDays(1).format(df));
			Assert.fail("Failure of testing setDueTime");
		} catch (IllegalArgumentException e) {

		}
	}
	
	
	@Test
	public void testGetDueTime() {
		Task t = new Task();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time = LocalDateTime.now().plusDays(1);
		time = time.minusNanos(time.getNano());
		try {
			t.getDueTime();
			Assert.fail("Failure of testing getDueTime");
		} catch (NullPointerException e) {
			
		}
		t.setDueTime(time.format(df));
		Assert.assertTrue(t.getDueTime().equals(time));
	}
	
	@Test
	public void testSetTaskId() {
		Task t = new Task();
		thrown.expect(IllegalArgumentException.class);
		t.setTaskId(-3);
	}
	
	@Test
	public void testGetTaskId() {
		Task t = new Task();
		try {
			t.getTaskId();
			Assert.fail("Failure of testing getTaskId");
		} catch (NullPointerException e) {
			
		}
		t.setTaskId(6);
		Assert.assertTrue(t.getTaskId() == 6);
	}
	
}
