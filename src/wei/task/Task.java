/**
 * This class is the details implementation of the Task class, it represent a 
 * Task with a few instance variables. e.g. taskId, taskName, etc. It also implements
 * the Serializable interface, so that the class can be used for a Object read and
 * write in the higher level management.
 */
package wei.task;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Wei Wang
 *
 */
public class Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int taskId;
	private String taskName;
	// private String taskText;
	private String project;
	private String status;
	private LocalDateTime creatTime;
	private LocalDateTime dueTime;

	/**
	 * Constructor for objects of class Task
	 */
	
	public Task(int id) {
		// initialise instance variables
		taskId = id;
		status = "undo";
		creatTime = LocalDateTime.now();
	}

	

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProject() {
		return this.project;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public LocalDateTime getCreatTime() {
		return this.creatTime;
	}

	public LocalDateTime getDueTime() {
		return this.dueTime;
	}

	public int getTaskId() {
		return this.taskId;
	}

}
