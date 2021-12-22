package ua.edu.sumdu.j2se.pavlenko.tasks.utils;

import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class Notification extends Thread{

    private TaskManagerView taskManagerView;
    private TaskManagerModel taskManagerModel;

    public Notification(TaskManagerView taskManagerView, TaskManagerModel taskManagerModel) {
        this.taskManagerView = taskManagerView;
        this.taskManagerModel = taskManagerModel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = taskManagerModel.getStartTask();
            if(result.length() > 0){
                taskManagerView.showNotification(result);
            }
        }
    }

}
