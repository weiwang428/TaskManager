package wei.task;

import java.lang.NullPointerException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.IllegalArgumentException;

/**
 * The task class represents a task with several properties, such as task name,
 * project name, due time etc. It also implements the Serializable interface, so
 * that the class can be used for object stream reader and writer.
 * 
 * @author Wei Wang
 * @version 1.0
 *
 */
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	private int taskId;
	private String taskName;
	private String project;
	private String status;
	private LocalDateTime creatTime;
	private LocalDateTime dueTime;

	/**
	 * Constructor for objects of class Task, the default value of task status is
	 * "undo", default create time is current system time. The taskId is initialized
	 * to be an invalid value(-1) and it needs the caller to update the taskId later
	 * on, the project, taskName and dueTime are initialized to be null, user must
	 * to update those properties.
	 */
	public Task() {
		taskId = -1;
		status = "undo";
		creatTime = LocalDateTime.now();
		project = null;
		taskName = null;
		dueTime = null;
	}

	/**
	 * Constructor for objects of class Task with a given taskId, the default value
	 * of status is "undo", default create time is current system time, the project,
	 * taskName and dueTime are initialized to be null, user must to update those
	 * properties.
	 *
	 * @param id The task's id, which is a non-negative integer.
	 * @throws IllegalArgumentException if the id is a negative integer.
	 */
	public Task(int id) throws IllegalArgumentException {
		this();
		this.setTaskId(id);
	}

	/**
	 * Set the new task name of a task, it can not be null or empty String, the
	 * taskName will be automatically trimmed.
	 *
	 * @param taskName new task name of the task
	 * @throws IllegalArgumentException if the specified name is null or empty
	 *                                  String.
	 */
	public void setTaskName(String taskName) throws IllegalArgumentException {
		if (taskName == null || taskName.trim().isEmpty()) {
			throw new IllegalArgumentException("Task name can not be empty!");
		}
		this.taskName = taskName.trim();
	}

	/**
	 * Return the task name of a task.
	 *
	 * @return task Name of the task
	 * @throws NullPointerException if the object is not properly initialized.
	 */
	public String getTaskName() throws NullPointerException {
		if (taskName == null)
			throw new NullPointerException();
		return this.taskName;
	}

	/**
	 * Set project name to a task, it can not be null or empty String, the
	 * projectName will be automatically trimmed.
	 *
	 * @param projectName New project name of the task
	 * @throws IllegalArgumentException if the specified projectName is null or
	 *                                  empty string.
	 */
	public void setProject(String projectName) throws IllegalArgumentException {
		if (projectName == null || projectName.trim().isEmpty()) {
			throw new IllegalArgumentException("Project can not be empty!");
		}
		this.project = projectName.trim();
	}

	/**
	 * Return the project name of the task.
	 *
	 * @return Project name of the task
	 * @throws NullPointerException if the object is not properly initialized.
	 */
	public String getProject() throws NullPointerException {
		if (this.project == null)
			throw new NullPointerException();
		return this.project;
	}

	/**
	 * Set the status of the task.
	 *
	 * @param status New status of the task, a legal status is "undo" or "done" and
	 *               it's case insensitive.
	 * @throws IllegalArgumentException if the specified status is null or not
	 *                                  "undo" and "done"
	 */
	public void setStatus(String status) throws IllegalArgumentException {
		if (status == null || (!(status.toLowerCase().equals("undo") || status.toLowerCase().equals("done")))) {
			throw new IllegalArgumentException("Please input the correct status, it has to be undo or done!");
		}
		this.status = status;
	}

	/**
	 * Return the status of the task.
	 *
	 * @return Task status of the task
	 * @throws NullPointerException if the object is not properly initialized.
	 */
	public String getStatus() throws NullPointerException {
		if (this.status == null)
			throw new NullPointerException();
		return this.status;
	}

	/**
	 * Return the create time of the task.
	 *
	 * @return Create time of the task.
	 */
	public LocalDateTime getCreatTime() {
		return this.creatTime;
	}

	/**
	 * Set dueTime of the task.
	 *
	 * @param dueTime A properly format String which will be set as due time, format
	 *                has to be: "yyyy-MM-dd HH:mm:ss"
	 * @throws IllegalArgumentException if the specified String format is invalid or
	 *                                  the new time is smaller than the Task's
	 *                                  create time.
	 */
	public void setDueTime(String dueTime) throws IllegalArgumentException {
		if (dueTime == null)
			throw new IllegalArgumentException("dueTime can not be null!");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			this.dueTime = LocalDateTime.parse(dueTime, df);
		} catch (Exception e) {
			throw new IllegalArgumentException("Due time format is invalid!");
		}
		// Make sure the due time is later than the creation time of the task.
		if (this.dueTime.compareTo(this.creatTime) < 0) {
			throw new IllegalArgumentException("Due time should later than create time!");
		}
	}

	/**
	 * Return the due time of the task.
	 *
	 * @return due time of the task
	 * @throws NullPointerException if the object is not properly initialized.
	 */
	public LocalDateTime getDueTime() {
		if (this.dueTime == null)
			throw new NullPointerException();
		return this.dueTime;
	}

	/**
	 * Set the task id of the task.
	 *
	 * @param taskId New task id of the task
	 * @throws IllegalArgumentException if task id is a negative integer
	 */
	public void setTaskId(int taskId) throws IllegalArgumentException {
		if (taskId < 0) {
			throw new IllegalArgumentException("Task id should be a positive integer!");
		}
		this.taskId = taskId;
	}

	/**
	 * Return the id of the task.
	 *
	 * @return task id of the task
	 * @throws NullPointerException if the object is not properly initialized.
	 */
	public int getTaskId() {
		if (this.taskId < 0)
			throw new NullPointerException();
		return this.taskId;
	}
}
