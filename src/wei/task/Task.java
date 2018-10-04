/**
 * This class is the details implementation of the Task class, it represent a 
 * Task with a few instance variables. e.g. taskId, taskName, etc. It also implements
 * the Serializable interface, so that the class can be used for a Object read and
 * write in the higher level management.
 */
package wei.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.IllegalArgumentException;

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
	private String project;
	private String status;
	private LocalDateTime creatTime;
	private LocalDateTime dueTime;

	/**
	 * Constructor for objects of class Task
	 */

	public Task() {
		// initialize instance variables
		status = "undo";
		creatTime = LocalDateTime.now();
	}
	/**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
	public Task(int id) {
		// initialize instance variables
		taskId = id;
		status = "undo";
		creatTime = LocalDateTime.now();
	}
	
	 /**
     * set task name to a task, task name can not be null or space, 
     *
     * @param taskName input a non-empty string as task name
     * @throws IllegalArgumentException {@inheritDoc}
     */
	public void setTaskName(String taskName) {
		if (taskName.trim().isEmpty()) {
			throw new IllegalArgumentException("Task name can not be empty!");
		}
		this.taskName = taskName.trim();
	}

	public String getTaskName() {
		return this.taskName;
	}
	
	/**
     * set project name to a task, project name can not be null or space, 
     *
     * @param projectName input a non-empty string as project name
     * @throws IllegalArgumentException {@inheritDoc}
     */
	public void setProject(String projectName) {
		if (projectName.trim().isEmpty()) {
			throw new IllegalArgumentException("Project can not be empty!");
		}
		this.project = projectName.trim();
	}

	public String getProject() {
		return this.project;
	}

	public void setStatus(String status) {
		if (!(status.toLowerCase().equals("undo") || status.toLowerCase().equals("done"))) {
			throw new IllegalArgumentException("Please input the correct status!");
		}
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

	public void setDueTime(String dueTime) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			this.dueTime = LocalDateTime.parse(dueTime, df);
		} catch (Exception e) {
			throw new IllegalArgumentException("Due time is invalid!");
		}
		if (this.dueTime.compareTo(this.creatTime) < 0) {
			throw new IllegalArgumentException("Due time should bigger than create time!");
		}
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getTaskId() {
		return this.taskId;
	}

}
