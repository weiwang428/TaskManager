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
	private TaskController taskController;
	private boolean exit;

	public MainEntry() {
		reader = new Scanner(System.in);
		this.taskController = new TaskController();
	}

	public void printWelcome() {
		System.out.println("****************************************************");
		System.out.println(">> Welcome to TaskManager");
		System.out.print(">> You have " + this.taskController.getUndoTaskNumber() + " tasks to do");
		System.out.println(" and " + this.taskController.getDoneTaskNumber() + " tasks are done!");
		System.out.println(">> Pick an option:");
		System.out.println(">> (1) Show Task List by date");
		System.out.println(">> (2) Show Task List by project");
		System.out.println(">> (3) Add a New Task");
		System.out.println(">> (4) Edit a Task");
		System.out.println(">> (5) Remove a Task");
		System.out.println(">> (6) Save");
		System.out.println(">> (7) Exit");
		System.out.println("****************************************************");
		System.out.println("                                                    ");
	}

	public void mainCommands() {
		int inputId = taskController.getInt("Please input the number of your option: ", "You must input an integer!");
		switch (inputId) {
		case 1:
			this.taskController.showTaskByTime();
			break;
		case 2:
			this.taskController.filterAProject();
			break;
		case 3:
			this.taskController.addTask();
			break;
		case 4:
			this.taskController.EditTask();
			break;
		case 5:
			this.taskController.removeTask();
			break;
		case 6:
			this.taskController.saveTaskList();
			break;
		case 7:
			this.exit = true;
			// save the task list before exit all the time.
			//this.taskController.saveTaskList();
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
