package ua.edu.sumdu.j2se.pavlenko.tasks;

import ua.edu.sumdu.j2se.pavlenko.tasks.controller.TaskManagerController;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.utils.Notification;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class Main {

	public static void main(String[] args) {

		TaskManagerModel taskManagerModel = new TaskManagerModel();
		TaskManagerView taskManagerView = new TaskManagerView();
		TaskManagerController taskManagerController = new TaskManagerController(taskManagerView, taskManagerModel);
		Notification notification = new Notification(taskManagerView, taskManagerModel);

		notification.setDaemon(true);
//		notification.start();

		taskManagerController.start();

		//2021-12-12T14:52:32

	}
}
