/**
 * 
 */
package wei.task;

import static org.junit.Assert.*;

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
public class TaskTest {

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
	public void test_TaskName() {
		Task t = new Task();
		try {
			t.setTaskName(null);
			Assert.fail("Failure of testing setTaskNme");
		} catch (IllegalArgumentException e) {
			
		}
		try {
			t.setTaskName("");
			Assert.fail("Failure of testing setTaskNme");
		} catch (IllegalArgumentException e) {

		}
		thrown.expect(IllegalArgumentException.class);
		t.setTaskName("");
		Assert.assertTrue(t.getTaskName().equals(""));
		
	}
	
	@Test
	public void test_Project() {
		Task t = new Task();
//		thrown = ExpectedException.none();
		thrown.expect(IllegalArgumentException.class);
		t.setProject(null);
//		thrown = ExpectedException.none();
//		thrown.expect(IllegalArgumentException.class);
		t.setProject("");
		t.setProject("Project1");
		Assert.assertTrue(t.getProject().equals("Project1"));
	}

}
