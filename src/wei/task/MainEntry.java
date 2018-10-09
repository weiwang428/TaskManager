package wei.task;

/**
 * This is the main class which contains the application entry point (main), it
 * will interact with the user, and provide a command line interface to manage
 * the task list, and also do some operation of the list.
 * 
 * @author Wei Wang
 * @version 1.0
 */
public class MainEntry {
	private Controller taskController;
	private boolean exit;

	/**
	 * Constructs a TaskController instance.
	 */
	public MainEntry() {
		this.taskController = new Controller();
	}

	/**
	 * Print welcome information to the user.
	 */
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
		System.out.println("****************************************************\n");
	}

	/**
	 * Main commands processing, handling various of user input.
	 */
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
			System.out.println("Thank you for coming, Bye!");
			this.exit = true;
			// save the task list before exit all the time.
			this.taskController.saveTaskList();
			break;
		default:
			System.out.println("This is not a valid option, please input 1 ~ 7.");
			break;
		}

	}

	/**
	 * Application entry point.
	 * 
	 * @param args not used for this application.
	 * 
	 */
	public static void main(String[] args) {
		MainEntry test = new MainEntry();
		// Continuously check the user input, until user exit.
		while (!test.exit) {
			test.printWelcome();
			test.mainCommands();
		}
	}

}
