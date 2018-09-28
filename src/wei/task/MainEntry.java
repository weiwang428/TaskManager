/**
 * This is the main class which contains the application entry point (main), it
 * will interact with the user, and provide a command line interface to manager
 * the todo list, and also do some operation of the list.
 */
package wei.task;

import java.util.Scanner;

/**
 * @author Wei Wang
 *
 */
public class MainEntry {
	private Scanner reader;
	private static String fileName = "taskList.bin";
	private TaskManager taskManager;
	private boolean exit;

	public MainEntry() {
		reader = new Scanner(System.in);
		taskManager = new TaskManager();
		taskManager.loadTaskListFromFile(MainEntry.fileName);
	}

	public void printWelcome() {
		System.out.println(">> Welcome to TaskManager");
		System.out.print(">> You have " + taskManager.getUndoTaskNum() + " tasks to do");
		System.out.println(" and " + taskManager.getDoneTaskNum() + " tasks are done!");
		System.out.println(">> Pick an option:");
		System.out.println(">> (1) Show Task List by date");
		System.out.println(">> (2) Show Task List by project");
		System.out.println(">> (3) Add a New Task");
		System.out.println(">> (4) Edit a Task");
		System.out.println(">> (5) Remove a Task");
		System.out.println(">> (6) Save");
		System.out.println(">> (7) Exit");
	}

	public void mainCommands() {

		int inputId;
		System.out.print("please input the number of your option: ");
		inputId = reader.nextInt();

		switch (inputId) {
		case 1:
			taskManager.sortByTime();
			taskManager.printTaskList();
			break;
		case 2:
			taskManager.filterByProject();
			break;
		case 3:
			taskManager.addTask();
			break;
		case 4:
			taskManager.EditTask();
			break;
		case 5:
			taskManager.removeTask();
			break;
		case 6:
			try {
				taskManager.SaveTaskListToFile(MainEntry.fileName);
			} catch (Exception e) {
				System.out.println(
						"Can not save the current task list from the file! The error information is showing below:");
				e.printStackTrace();
			}
			break;
		case 7:
			this.exit = true;
			// save the task list before exit all the time.
			try {
				taskManager.SaveTaskListToFile(MainEntry.fileName);
			} catch (Exception e) {
				System.out.println(
						"Can not save the current task list from the file! The error information is showing below:");
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("This is not a valid option, please input 1 ~ 7.");
			break;
		}

	}

	public static void main(String[] args) {
		MainEntry test = new MainEntry();
		// Continuously check the user input, until user exit.
		while (!test.exit) {
			test.printWelcome();
			test.mainCommands();
		}

	}

}
