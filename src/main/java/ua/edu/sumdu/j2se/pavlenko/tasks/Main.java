package ua.edu.sumdu.j2se.pavlenko.tasks;

import ua.edu.sumdu.j2se.pavlenko.tasks.controller.TaskManagerController;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class Main {

	public static void main(String[] args) {
		/*Task task1 = new Task("task1", 10);
		task1.setActive(true);
		Task task2 = new Task("task2", 10, 40, 10);
		task2.setActive(true);
		Task task3 = new Task("task3", 20);
		task3.setActive(true);
		Task task4 = new Task("task4", 20, 50, 20);
		task4.setActive(true);
		Task task5 = new Task("task5", 30);
		task5.setActive(true);
		Task task6 = new Task("task6", 30, 60, 20);
		task6.setActive(true);
		Task task7 = new Task("task7", 40);
		task7.setActive(true);
		Task task8 = new Task("task8", 50);
		task8.setActive(true);
		Task task9 = new Task("task9", 60);
		task9.setActive(true);
		ArrayTaskList arrayTaskList = new ArrayTaskList();
		arrayTaskList.add(task1);
		arrayTaskList.add(task2);
		arrayTaskList.add(task3);
		arrayTaskList.add(task4);
		arrayTaskList.add(task5);
		arrayTaskList.add(task6);
		arrayTaskList.add(task7);
		arrayTaskList.add(task8);
		arrayTaskList.add(task9);
		System.out.println(arrayTaskList);
		System.out.println(arrayTaskList.incoming(10, 60));
		LinkedTaskList linkedTaskList = new LinkedTaskList();
		linkedTaskList.add(task1);
		linkedTaskList.add(task2);
		linkedTaskList.add(task3);
		linkedTaskList.add(task4);
		linkedTaskList.add(task5);
		linkedTaskList.add(task6);
		linkedTaskList.add(task7);
		linkedTaskList.add(task8);
		linkedTaskList.add(task9);
		System.out.println(linkedTaskList);
		System.out.println(linkedTaskList.incoming(30, 50));
		System.out.println("Hello");

		for (Task value : linkedTaskList.incoming(30, 50)) {
			System.out.println(value);
		}
		for (Task task : arrayTaskList.incoming(30, 50)) {
			System.out.println(task);
		}

		Task t = task1.clone();
		ArrayTaskList nls = arrayTaskList.clone();
		LinkedTaskList nList = linkedTaskList.clone();
		for (Task task : nls){
			System.out.println(task);
		}*/
		TaskManagerModel taskManagerModel = new TaskManagerModel();
		TaskManagerView view = new TaskManagerView();
		TaskManagerController taskManagerController = new TaskManagerController(view, taskManagerModel);

		taskManagerController.start();

		//2021-12-12T14:52:32

		/*int selectedFunc = view.getSelectedOption(view.navigationPages(TaskManagerView.startPageContent));
		if(selectedFunc == 1) {
			selectedFunc = view.getSelectedOption(view.navigationPages(TaskManagerView.createTaskPageContent));
			if(selectedFunc == 1) {
				String title = view.getNewTitle();
				LocalDateTime time = Adapter.timeAdapter(view.getNewStartTime());
				Task newTask = new Task(title, time);
				newTask.setActive(view.getNewActivation());
				taskManagerModel.add(newTask);
			} else {
				String title = view.getNewTitle();
				LocalDateTime startTime = Adapter.timeAdapter(view.getNewStartTime());
				LocalDateTime endTime = Adapter.timeAdapter(view.getNewEndTime());
				int interval = view.getNewInterval();
				Task newTask = new Task(title, startTime, endTime, interval);
				newTask.setActive(view.getNewActivation());
				taskManagerModel.add(newTask);
			}
		}*/
		//System.out.println(taskManagerModel);
		//System.out.println("selectedFunc: " + selectedFunc);
		//1
		//LocalDateTime time = Adapter.timeAdapter("11.12.2021T10:12:13");
		//2
		//LocalDateTime time = Adapter.timeAdapter("2007-12-03T10:15:30");
		//System.out.println(time);
	}
}
