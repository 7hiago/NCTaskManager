package ua.edu.sumdu.j2se.pavlenko.tasks;

import ua.edu.sumdu.j2se.pavlenko.tasks.controller.TaskManagerController;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class Main {

	public static void main(String[] args) {

		TaskManagerModel taskManagerModel = new TaskManagerModel();
		TaskManagerView view = new TaskManagerView();
		TaskManagerController taskManagerController = new TaskManagerController(view, taskManagerModel);

		taskManagerController.start();

		//2021-12-12T14:52:32

	}
}
