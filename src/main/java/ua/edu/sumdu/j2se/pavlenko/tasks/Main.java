package ua.edu.sumdu.j2se.pavlenko.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pavlenko.tasks.controller.TaskManagerController;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		logger.info("Start executing main program");
		TaskManagerModel taskManagerModel = new TaskManagerModel();
		TaskManagerView taskManagerView = new TaskManagerView();
		TaskManagerController taskManagerController = new TaskManagerController(taskManagerView, taskManagerModel);

		taskManagerController.start();

	}
}
