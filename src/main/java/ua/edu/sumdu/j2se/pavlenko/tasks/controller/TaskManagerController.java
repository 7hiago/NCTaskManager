package ua.edu.sumdu.j2se.pavlenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class TaskManagerController {
    private static Logger logger = Logger.getLogger(TaskManagerController.class);
    private TaskManagerView taskManagerView;
    private TaskManagerModel taskManagerModel;
    private Notification notification;

    public TaskManagerController(TaskManagerView taskManagerView, TaskManagerModel taskManagerModel) {
        this.taskManagerView = taskManagerView;
        this.taskManagerModel = taskManagerModel;
        notification = new Notification(taskManagerView, taskManagerModel);
    }

    public void start() {
        logger.debug("invocation start() method");
        logger.info("Start executing TaskManager");
        taskManagerModel.loadDataFromFile();

        initializeNotification();

        while (true) {
            switch (taskManagerView.showStartPage()) {
                case 1:
                    createTaskHandler();
                    break;
                case 2:
                    changeTaskHandler();
                    break;
                case 3:
                    removeTaskHandler();
                    break;
                case 4:
                    watchListHandler();
                    break;
                case 5:
                    watchCalendarHandler();
                    break;
                case 6:
                    close();
                    break;
                default:
                    break;
            }
        }
    }

    public void close() {
        logger.debug("invocation close() method");
        taskManagerModel.saveDataToFile();
        logger.info("Stop executing TaskManager with status 0");
        System.exit(0);
    }

    public void initializeNotification() {
        logger.debug("invocation initializeNotification() method");
        notification.setDaemon(true);
        notification.start();
    }

    public void createTaskHandler() {
        logger.debug("invocation createTaskHandler() method");
        switch (taskManagerView.showCreateTaskPage()) {
            case 1:
                taskManagerModel.createNotRepeatedTask(taskManagerView.getTitle(), taskManagerView.getStartTime());
                break;
            case 2:
                taskManagerModel.createRepeatedTask(taskManagerView.getTitle(), taskManagerView.getStartTime(), taskManagerView.getEndTime(), taskManagerView.getInterval());
                break;
            default:
                break;
        }
        taskManagerModel.saveDataToFile();
    }

    public void changeTaskHandler() {
        logger.debug("invocation changeTaskHandler() method");
        int userChangeTask = taskManagerView.showActualTaskListPageToChange(taskManagerModel.getActualTaskList());
        switch (taskManagerView.showChangeTaskPage()) {
            case 1:
                taskManagerModel.changeTaskTitle(userChangeTask, taskManagerView.getTitle());
                break;
            case 2:
                taskManagerModel.changeTaskActivation(userChangeTask, taskManagerView.getActivation());
                break;
            case 3:
                taskManagerModel.changeTaskStartTime(userChangeTask, taskManagerView.getStartTime());
                break;
            case 4:
                taskManagerModel.changeTaskEndTime(userChangeTask, taskManagerView.getEndTime());
                break;
            case 5:
                taskManagerModel.changeTaskInterval(userChangeTask, taskManagerView.getInterval());
                break;
            default:
                break;
        }
        taskManagerModel.saveDataToFile();
    }

    public void removeTaskHandler() {
        logger.debug("invocation removeTaskHandler() method");
        int userRemoveTask = taskManagerView.showActualTaskListPageToChange(taskManagerModel.getActualTaskList());
        if(userRemoveTask > 0) taskManagerModel.removeTask(userRemoveTask);
        taskManagerModel.saveDataToFile();
    }

    public void watchListHandler() {
        logger.debug("invocation watchListHandler() method");
        taskManagerView.showActualTaskListPage(taskManagerModel.getActualTaskList());
    }

    public void watchCalendarHandler() {
        logger.debug("invocation watchCalendarHandler() method");
        String[] userCalendar = taskManagerModel.getCalendar(taskManagerView.getStartTime(), taskManagerView.getEndTime());
        taskManagerView.showCalendarPage(userCalendar);
    }

}
