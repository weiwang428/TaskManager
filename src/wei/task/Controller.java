package wei.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class is the Controller which interacts with user, user can input
 * commands, Controller call the methods in TaskManager, e.g. add new task,
 * update a task, print the task, remove a task, etc.
 * 
 * @author Wei Wang
 * @version 1.0
 */
public class Controller {
	private TaskManager taskMng;
	private Scanner reader;
	private final String fileName = "taskList.bin";

	/**
	 * Constructs a Controller, load tasks from the file: {@value fileName}, the
	 * file has to be exist in the same directly with the main entry point of the
	 * application. The file format has to be a binary file which contains a list of
	 * task objects, When the loading fails, an error message will be printed to the
	 * console.
	 *
	 */
	public Controller() {
		taskMng = new TaskManager();
		reader = new Scanner(System.in);
		try {
			this.taskMng.loadTaskListFromFile(this.fileName);
		} catch (Exception e) {
			System.out.println(String.format("Error when loading configuration file: %s, error message is: %s",
					this.fileName, e.getMessage()));
		}
	}

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
		taskMng.printProjectNames();
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
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.print("Please input due time, format like \"2018-09-28 17:07:05\": ");
		try {
			LocalDateTime dueTime = LocalDateTime.parse(reader.nextLine(), df);
			tempTask.setDueTime(dueTime);
			return true;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}
		catch (Exception e) {
			System.out.println("Due time format is invalid!");
			return false;
		}
	}
	
	/**
	 * Get an integer from the user, if the input is invalid, continuously reading
	 * until user input a legal integer.
	 * 
	 * @param inputHint    The hint information which will print to the user.
	 * @param errorMessage error information when the reading fails
	 * @return A legal integer.
	 */
	public int getInt(String inputHint, String errorMessage) {
		boolean ifInteger = false;
		int inputId = 0;
		while (!ifInteger) {
			System.out.print(inputHint);
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

	/**
	 * The addTask method enables the user to add a new task to TaskManager, user
	 * will set task name, project name and due time for the new task.
	 *
	 */
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
	 * The EditTask enables the user to update a certain task in TaskManager, user
	 * can choose to modify the task name, project name, status and due time.
	 * 
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
		if (line.toLowerCase().trim().equals("yes")) {
			while (!updateTaskName(task))
				;
		}
		System.out.print("Do you want to edit project name? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().trim().equals("yes")) {
			while (!updateProjectName(task))
				;
		}
		System.out.print("Do you want to edit task status? yes/no: ");
		line = reader.nextLine();
		if (line.toLowerCase().trim().equals("yes")) {
			while (!updateStatus(task))
				;
		}
		System.out.print("Do you want to edit due time? yes/no: ");
		line = reader.nextLine();
		if (line.trim().equals("yes")) {
			while (!updateDueTime(task))
				;
		}
	}

	/**
	 * The removeTask enables the user to remove a task from the TaskManager, user
	 * can specify the task id which he wants to remove.
	 *
	 */
	public void removeTask() {
		int inputId = getInt("Please input the task ID which you want to remove: ", "Task id needs to be an integer!");
		if(taskMng.removeTask(inputId)){
			System.out.println("Delete successfully!");
		}
		else {
			System.out.println("Error occurs during deleting!");
		}
	}

	/**
	 * The filterAProject enables user to show tasks by a certain project name, user
	 * can specify the project name which he wants to filter.
	 *
	 */
	public void filterAProject() {
		if(taskMng.size() >0 )
			taskMng.printProjectNames();
		String project;
		System.out.print("Please input the project name you want to filter: ");
		project = reader.nextLine();
		if(project != null && !project.trim().isEmpty()) {
		taskMng.printTaskByFilterProject(project);
		}
		else {
			System.out.println("Project name can not be null or space!");
		}
	}

	/**
	 * The showTaskByTime will show all the tasks with the sorting order of create
	 * time.
	 * 
	 */
	public void showTaskByTime() {
		this.taskMng.sortByTime();
		this.taskMng.printTaskList();
	}

	/**
	 * Return the number of the undo tasks in the TaskManager.
	 * 
	 * @return The number of the undo tasks in the TaskManager
	 * 
	 */
	public int getUndoTaskNumber() {
		return this.taskMng.getUndoTaskNum();
	}

	/**
	 * Return the number of the done tasks in the TaskManager.
	 * 
	 * @return The number of the done tasks in the TaskManager
	 * 
	 */
	public int getDoneTaskNumber() {
		return this.taskMng.getDoneTaskNum();
	}

	/**
	 * Save changes of all the current tasks into the file: {@value fileName} with a
	 * binary format, it can be loaded next time when initialize the
	 * {@code Controller}.
	 * 
	 */
	public void saveTaskList() {
		try {
			this.taskMng.SaveTaskListToFile(this.fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}