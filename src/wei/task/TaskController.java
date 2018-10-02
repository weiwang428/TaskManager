/**
 * This class is the Task Manager which maintain a list of Tasks, it provides
 * the user with some manipulation of the list, e.g. add new task, update a task,
 * print the task, remove a task, etc.
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
		this.taskMng.loadTaskListFromFile(this.fileName);
	}

	/**
	 * add a task to the arraylist,user can define task name,project name. Status
	 * has a default value "undo",system specify "taskCounter" as the default
	 * taskId,
	 * 
	 * @param no parameter
	 * @return void
	 */

	public boolean updateTaskName(Task tempTask) {
		String line;
		System.out.println("please input task name.");
		line = reader.nextLine();
		try {
			tempTask.setTaskName(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean updateStatus(Task tempTask) {
		String line;
		System.out.println("please input status. undo/done");
		line = reader.nextLine();
		try {
			tempTask.setStatus(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean updateProjectName(Task tempTask) {
		String line;
		System.out.println("please input project name.");
		line = reader.nextLine();
		try {
			tempTask.setProject(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean updateDueTime(Task tempTask) {
		String line;
		System.out.println("please input due time,format like \"2018-09-28 17:07:05\".");
		line = reader.nextLine();
		try {
			tempTask.setDueTime(line);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
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
		System.out.print("please input the task ID you want to edit: ");
		int inputId = reader.nextInt();
		Task task = taskMng.getTaskById(inputId);
		if (task == null) {
			System.out.println("there is no such a task");
			return;
		}
		System.out.print("Do you want to edit task name? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().equals("yes")) {
			System.out.println("please input task name");
			line = reader.nextLine();
			while (!updateTaskName(task))
				;
		}
		System.out.print("Do you want to edit project name? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().equals("yes")) {
			System.out.println("please input task name");
			line = reader.nextLine();
			while (!updateProjectName(task))
				;
		}
		System.out.print("Do you want to edit task status? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().equals("yes")) {
			System.out.println("please input status,like 'undo','done'");
			line = reader.nextLine();
			while (!updateStatus(task))
				;
		}
		System.out.print("Do you want to edit due time? yes/no: ");
		line = reader.nextLine();
		if (line.equals("yes")) {
			System.out.println("please input due time,format like \"2018-09-28 17:07:05\".");
			line = reader.nextLine();
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
		System.out.println("please input the task ID you want to delete");
		try {
			inputId = reader.nextInt();
			taskMng.removeTask(inputId);
		} catch (Exception e) {
			System.out.println("please input a legal task ID,task ID shold be an integer");
		}
	}

	public void filterAProject() {
		String project;
		System.out.println("please input the project name you want to filter!");
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
