package ua.edu.sumdu.j2se.pavlenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class Notification extends Thread{
    private static Logger logger = Logger.getLogger(Notification.class);
    private TaskManagerView taskManagerView;
    private TaskManagerModel taskManagerModel;

    public Notification(TaskManagerView taskManagerView, TaskManagerModel taskManagerModel) {
        this.taskManagerView = taskManagerView;
        this.taskManagerModel = taskManagerModel;
    }

    @Override
    public void run() {
        logger.debug("invocation run() method");
        logger.info("Start notification thread");
        while (true) {
            try {
                sleep(30000);
            } catch (InterruptedException exception) {
                logger.error("Notification thread exception occur\n" + exception);
            }
            String result = taskManagerModel.getStartRunningTasks();
            if(result.length() > 0){
                logger.info("Notification executed");
                taskManagerView.showNotification(result);
            }
        }
    }

}
