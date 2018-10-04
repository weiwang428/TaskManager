/**
 * This class is the Task Manager Controller which maintain the interactive with user, 
 * user can input commands, Task Manager Controller call the methods in Task Manager
 * e.g. add new task, update a task,print the task, remove a task, etc.
 */
package wei.task;

import java.util.*;
import java.io.*;

/**
 * @author Wei Wang
 *
 */
public class TaskController {
	private TaskManager taskMng;
	private Scanner reader;
	private static String fileName = "taskList.bin";

	/**
	 * Constructor for objects of class TaskManager
	 */
	public TaskController() {
		taskMng = new TaskManager();
		reader = new Scanner(System.in);
		try {
			this.taskMng.loadTaskListFromFile(this.fileName);
		} catch (Exception e) {
			System.out.println(String.format("Error when loading configuration file: %sm, error message is: %s",
					this.fileName, e.getMessage()));
		}
	}

	/**
	 * add a task to the arraylist,user can define task name,project name. Status
	 * has a default value "undo",system specify "taskCounter" as the default
	 * taskId,
	 * 
	 * @param no parameter
	 * @return void
	 */

	private boolean updateTaskName(Task tempTask) {
		System.out.print("Please input task name: ");
		String line = reader.nextLine();
		try {
			tempTask.setTaskName(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private boolean updateStatus(Task tempTask) {
		System.out.print("Please input status. undo/done : ");
		String line = reader.nextLine();
		try {
			tempTask.setStatus(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private boolean updateProjectName(Task tempTask) {
		System.out.print("Please input project name: ");
		String line = reader.nextLine();
		try {
			tempTask.setProject(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private boolean updateDueTime(Task tempTask) {
		System.out.print("Please input due time,format like \"2018-09-28 17:07:05\": ");
		String line = reader.nextLine();
		try {
			tempTask.setDueTime(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getInt(String prompt, String errorMessage) {
		boolean ifInteger = false;
		int inputId = 0;
		while (!ifInteger) {
			System.out.print(prompt);
			try {
				inputId = reader.nextInt();
				// Clear the end of line by the integer!
				reader.nextLine();
				ifInteger = true;
			} catch (Exception e) {
				System.out.println(errorMessage);
				// Clear the invalid input!
				reader.nextLine();
			}
		}
		return inputId;
	}

	public void addTask() {
		Task task = new Task();
		while (!updateTaskName(task))
			;
		while (!updateProjectName(task))
			;
		while (!updateDueTime(task))
			;
		taskMng.addTask(task);
	}

	/**
	 * edit a task of the arraylist,user can modify task name, project,and change
	 * status, due time
	 * 
	 * @param no parameter
	 * @return void
	 */
	public void EditTask() {
		String line;
		int inputId = getInt("Please input the task ID you want to edit: ", "Task id should be an integer!");
		Task task = taskMng.getTaskById(inputId);
		if (task == null) {
			System.out.println("There is no such a task");
			return;
		}
		System.out.print("Do you want to edit task name? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().equals("yes")) {
			while (!updateTaskName(task))
				;
		}
		System.out.print("Do you want to edit project name? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().equals("yes")) {
			while (!updateProjectName(task))
				;
		}
		System.out.print("Do you want to edit task status? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().equals("yes")) {
			while (!updateStatus(task))
				;
		}
		System.out.print("Do you want to edit due time? yes/no: ");
		line = reader.nextLine();
		if (line.equals("yes")) {
			while (!updateDueTime(task))
				;
		}
	}

	/**
	 * delete a task from the list, user can specify the task id as an index
	 *
	 */
	public void removeTask() {
		int inputId;
		String line;
		System.out.print("Please input the task ID you want to delete: ");
		try {
			line = reader.nextLine();
			inputId = Integer.parseInt(line);
			taskMng.removeTask(inputId);
		} catch (Exception e) {
			System.out.println("Please input a legal task ID,task ID shold be an integer!");
		}
	}

	public void filterAProject() {
		String project;
		System.out.print("Please input the project name you want to filter: ");
		project = reader.next();
		taskMng.filterByProject(project);
	}

	public void showTaskByTime() {
		this.taskMng.sortByTime();
		this.taskMng.printTaskList();
	}

	public void statusCheck() {

	}

	public int getUndoTaskNumber() {
		return this.taskMng.getUndoTaskNum();
	}

	public int getDoneTaskNumber() {
		return this.taskMng.getDoneTaskNum();
	}

	public void saveTaskList() {
		try {
			this.taskMng.SaveTaskListToFile(this.fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
