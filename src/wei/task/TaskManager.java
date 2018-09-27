/**
 * This class is the Task Manager which maintain a list of Tasks, it provides
 * the user with some manipulation of the list, e.g. add new task, update a task,
 * print the task, remove a task, etc.
 */
package wei.task;

//import java.util.ArrayList;
import java.util.*;
import java.io.*;

/**
 * @author Wei Wang
 *
 */
public class TaskManager {
	private ArrayList<Task> taskList;
	private Scanner reader;
	private static int taskCounter;

	/**
	 * Constructor for objects of class TaskManager
	 */
	public TaskManager() {
		// initialise instance variables
		taskList = new ArrayList<Task>();
		reader = new Scanner(System.in);
	}

	/**
	 * An example of a method - replace this comment with your own
	 *
	 * @param y a sample parameter for a method
	 * @return the sum of x and y
	 */
	public void addTask() {
		// put your code here
		String inputLine;
		Task tempTask = new Task(taskCounter++);
		System.out.println("please input task name");
		inputLine = reader.nextLine();
		tempTask.setTaskName(inputLine);
		System.out.println("please input project name");
		inputLine = reader.nextLine();
		tempTask.setProject(inputLine);
		taskList.add(tempTask);
	}

	public void printTask(Task task) {
		// put your code here
		System.out.println("-----------------------------------------------");
		System.out.println("task ID: " + task.getTaskId());
		System.out.println("task name: " + task.getTaskName());
		System.out.println("task project: " + task.getProject());
		System.out.println("task status: " + task.getStatus());
		System.out.println("task create time: " + task.getCreatTime());
		System.out.println("-----------------------------------------------");
	}

	public void printTaskList() {
		// put your code here
		for (Task t : taskList) {
			printTask(t);
		}
	}

	public void EditTask() {
		String inputLine;
		int inputId;
		System.out.println("please input the task ID you want to edit");
		inputId = reader.nextInt();
		boolean exist = false;
		for (Task t : taskList) {
			if (t.getTaskId() == inputId) {
				System.out.println("if you want to edit task name,print yes");
				inputLine = reader.nextLine();
				if (inputLine.equals("yes")) {
					System.out.println("please input task name");
					inputLine = reader.nextLine();
					t.setTaskName(inputLine);
				}
				System.out.println("if you want to edit project name,print yes");
				inputLine = reader.nextLine();
				if (inputLine.equals("yes")) {
					System.out.println("please input task name");
					inputLine = reader.nextLine();
					t.setProject(inputLine);
				}
				System.out.println("if you want to edit task status,print yes");
				inputLine = reader.nextLine();
				if (inputLine.equals("yes")) {
					System.out.println("please input status,like 'undo','done'");
					inputLine = reader.nextLine();
					t.setStatus(inputLine);
				}

				exist = true;
				break;
			}
			if (!exist) {
				System.out.println("there is no such a task");
			}
		}

	}

	public void sortByTime() {
		taskList.sort(Comparator.comparing(Task::getCreatTime));
	}

	public void removeTask() {
		int inputId;
		boolean exist = false;
		System.out.println("please input the task ID you want to delete");
		try {
			inputId = reader.nextInt();

			for (Task t : taskList) {
				if (t.getTaskId() == inputId) {
					taskList.remove(inputId);
				}
				exist = true;
				break;
			}
			if (!exist) {
				System.out.println("there is no such a task");
			}

		} catch (Exception e) {

			System.out.println("please input a legal task ID,task ID shold be an integer");
		}

	}

	public boolean loadTaskListFromFile(String fileName) {
		taskList.clear();
		ObjectInputStream objReader = null;

		try {
			objReader = new ObjectInputStream(new FileInputStream(fileName));
			// Read the line until end of the file.
			Object newObj = objReader.readObject();
			while (newObj != null) {
				taskList.add((Task) newObj);
				newObj = objReader.readObject();
			}
		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			System.out.println(String.format("File: %s does not exist!", fileName));
			return false;
		} catch (Exception e) {
			System.out.println(String.format("Error happen when reading from file: %s, error message is: %s", fileName,
					e.getMessage()));
			return false;
		} finally {
			// objReader.close();
		}

		// Always put the task_counter to the maximum task id which we load from the
		// file, and then increment it to hold the next task id.
		taskCounter = taskList.size() > 0
				? taskList.stream().mapToInt(t -> t.getTaskId()).max().orElseThrow(NoSuchElementException::new)
				: 0;
		taskCounter++;
		return true;
	}

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

	public void filterByProject() {

		// taskList.stream().mapToInt(t ->
		// t.getTaskId()).max().orElseThrow(NoSuchElementException::new)
		String projectName;
		System.out.println("please input the project name you want to filter!");
		projectName = reader.next();
		for (Task t : taskList) {
			if (t.getProject().equals(projectName)) {
				printTask(t);
			}
		}
	}

	public void statusCheck() {

	}

	public int getUndoTaskNum() {
		return (int) taskList.stream().filter(t -> t.getStatus().toLowerCase().compareTo("undo") == 0).count();
	}

	public int getDoneTaskNum() {
		return (int) taskList.stream().filter(t -> t.getStatus().toLowerCase().compareTo("done") == 0).count();
	}

}
