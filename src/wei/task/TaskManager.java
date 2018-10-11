package wei.task;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * This class is the Task Manager which maintains a list of {@link Task}, it
 * provides some functions to manipulate the task list, e.g. add a new task,
 * print the task list, update a task, remove a task, etc.
 * 
 * @author Wei Wang
 * @version 1.0
 * 
 */
public class TaskManager {
	private List<Task> taskList;
	private int taskCounter;

	/**
	 * Constructor for objects of class TaskManager, initialize an empty list of
	 * task.
	 */
	public TaskManager() {
		taskList = new ArrayList<Task>();
	}

	/**
	 * The addTask method enables user to add tasks to the TaskManager.
	 * 
	 * @param task A new task to be added, if the task is null, nothing will be
	 *             added
	 * @see Task
	 * 
	 */
	public void addTask(Task task) {
		if (task != null) {
			task.setTaskId(taskCounter++);
			taskList.add(task);
		}
	}

	/**
	 * The printTaskList method will print all the tasks with details information to
	 * the console.
	 * 
	 */
	public void printTaskList() {
		for (Task t : taskList) {
			printTask(t);
		}
	}

	/**
	 * Print all the current available project names to the console, if the
	 * TaskManager is empty, nothing will be print.
	 * 
	 */
	public void printProjectNames() {
		if (taskList.size() > 0) {
			System.out.print("The current available project names are: ");
			System.out.println(taskList.stream().map(t -> t.getProject()).distinct().collect(Collectors.joining(", ")));
		}
	}

	/**
	 * The getTaskById method will return a task with a specified id to the user.
	 * 
	 * @param inputId the task id which user wants to search for.
	 * @return return a task instance with the specified id, if the task isn't
	 *         found, return null
	 */
	public Task getTaskById(int inputId) {
		for (Task t : taskList) {
			if (t.getTaskId() == inputId) {
				return t;
			}
		}
		return null;
	}

	/**
	 * The sortByTime method will sort all the tasks by the task's create time in a
	 * ascending order.
	 * 
	 */
	public void sortByTime() {
		taskList.sort(Comparator.comparing(Task::getCreatTime));
	}

	/**
	 * The removeTask method enables user to remove a task from the TaskManager with
	 * a given task id, if no task is found with the given task id, then a prompt
	 * will be printed to the user.
	 *
	 * @param inputId the task id which user want to remove.
	 */
	public void removeTask(int inputId) {
		for (Task t : taskList) {
			if (t.getTaskId() == inputId) {
				taskList.remove(t);
				return;
			}
		}
		System.out.println("there is no such a task");
	}

	/**
	 * The loadTaskListFromFile method will read tasks from the given file, The file
	 * is expected to be in a binary format with all the tasks written by the
	 * {@link #SaveTaskListToFile(String)} object.
	 * 
	 * @param fileName File name of the binary file which contains the saved tasks
	 * @return {@code true} if the reading is successful
	 * @throws IOException error when the reading fails
	 */
	public boolean loadTaskListFromFile(String fileName) throws IOException {
		// use java Serializable to implement reading objects from a binary file.
		ObjectInputStream objReader = null;
		try {
			objReader = new ObjectInputStream(new FileInputStream(fileName));
			// Clear all the current tasks from the list.
			taskList.clear();
			// Read the task object until end of the file is reached.
			Object newObj = objReader.readObject();
			while (newObj != null) {
				taskList.add((Task) newObj);
				newObj = objReader.readObject();
			}
		} catch (EOFException e) {
			// Nothing needs to be done when we met end of line.
		} catch (FileNotFoundException e) {
			System.out.println(String.format("File: %s does not exist!", fileName));
			return false;
		} catch (Exception e) {
			System.out.println(String.format("Error happen when reading from file: %s, error message is: %s", fileName,
					e.getMessage()));
			return false;
		} finally {
			objReader.close();
		}
		// Always put the task_counter to the maximum task id which we load from the
		// file, and then increment it to hold the next task id.
		taskCounter = taskList.size() > 0
				? taskList.stream().mapToInt(t -> t.getTaskId()).max().orElseThrow(NoSuchElementException::new)
				: -1;
		taskCounter++;
		return true;
	}

	/**
	 * The SaveTaskListToFile method will write the task list to the file with
	 * binary format, it can be loaded by the {@link #loadTaskListFromFile(String)}
	 * method.
	 * 
	 * @param fileName file name of the binary file which contains the saved list
	 * @return {@code true} if the writing is successful
	 * @throws IOException error when writing fails
	 */
	public boolean SaveTaskListToFile(String fileName) throws IOException {
		ObjectOutputStream objWriter = null;
		try {
			objWriter = new ObjectOutputStream(new FileOutputStream(fileName));
			for (Task t : taskList) {
				objWriter.writeObject(t);
			}
		} catch (Exception e) {
			System.out.println(String.format("Error happen when writing to file: %s, error message is: %s", fileName,
					e.getMessage()));
			return false;
		} finally {
			objWriter.close();
		}
		return true;
	}

	/**
	 * The printTaskByFilterProject will show tasks by a project name, if none of
	 * the task belongs to the given project name, then an error message will be
	 * printed to the console.
	 *
	 * @param projectName the project name which user want to search for the
	 *                    printing.
	 */
	public void printTaskByFilterProject(String projectName) {
		boolean ifExist = false;
		for (Task t : taskList) {
			if (t.getProject().equals(projectName)) {
				printTask(t);
				ifExist = true;
			}
		}
		if (!ifExist) {
			System.out.println(String.format("There is no task has a project name: %s.", projectName));
		}
	}

	/**
	 * Return the number of the undo tasks in the TaskManager.
	 * 
	 * @return The number of the undo tasks in the TaskManager.
	 * 
	 */
	public int getUndoTaskNum() {
		return (int) taskList.stream().filter(t -> t.getStatus().toLowerCase().compareTo("undo") == 0).count();
	}

	/**
	 * Return the number of the done tasks in the TaskManager.
	 * 
	 * @return The number of the done tasks in the TaskManager.
	 * 
	 */
	public int getDoneTaskNum() {
		return (int) taskList.stream().filter(t -> t.getStatus().toLowerCase().compareTo("done") == 0).count();
	}

	/**
	 * Print the details information of a task to the console.
	 * 
	 * @param task Task which the user wants to print
	 */
	private void printTask(Task task) {
		// Use a proper format to output the date/time information of the Task.
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println("-----------------------------------------------");
		System.out.println("task ID: " + task.getTaskId());
		System.out.println("task name: " + task.getTaskName());
		System.out.println("task project: " + task.getProject());
		System.out.println("task status: " + task.getStatus());
		System.out.println("task create time: " + task.getCreatTime().format(df));
		System.out.println("task due time: " + task.getDueTime().format(df));
		System.out.println("-----------------------------------------------\n");
	}
}
