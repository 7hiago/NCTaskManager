package ua.edu.sumdu.j2se.pavlenko.tasks.controller;

import ua.edu.sumdu.j2se.pavlenko.tasks.model.TaskManagerModel;
import ua.edu.sumdu.j2se.pavlenko.tasks.view.TaskManagerView;

public class TaskManagerController {

    private TaskManagerView taskManagerView;
    private TaskManagerModel taskManagerModel;

    public TaskManagerController(TaskManagerView taskManagerView, TaskManagerModel taskManagerModel) {
        this.taskManagerView = taskManagerView;
        this.taskManagerModel = taskManagerModel;
    }

    public void start() {
        try {
            taskManagerModel.loadDataFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for( ; ; ) {
            switch (taskManagerView.showStartPage()) {
                case 1:
                    if(!createTaskHandler()) continue;
                    break;
                case 2:
                    if(!changeTaskHandler()) continue;
                    break;
                case 3:
                    if(!removeTaskHandler()) continue;
                    break;
                case 4:
                    if(!watchListHandler()) continue;
                    break;
                case 5:
                    if(!watchCalendarHandler()) continue;
                    break;
                case 6:
                    close();
                    break;
            }
        }
    }

    public void close() {
        try {
            taskManagerModel.saveDataToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void notification() {

    }

    public boolean createTaskHandler() {
        switch (taskManagerView.showCreateTaskPage()) {
            case 1:
                taskManagerModel.createNotRepeatedTask(taskManagerView.getTitle(), taskManagerView.getStartTime());
                break;
            case 2:
                taskManagerModel.createRepeatedTask(taskManagerView.getTitle(), taskManagerView.getStartTime(), taskManagerView.getEndTime(), taskManagerView.getInterval());
                break;
            case 3: //back
                return false;
            default:
                System.out.println("Please, chose exist option!");
                break;
        }
        return true;
    }

    public boolean changeTaskHandler() {
        int userChangeTask = taskManagerView.showActualTaskListPage(taskManagerModel.getActualTaskList());
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
            case 6: //back
                return false;
            default:
                break;
        }
        return true;
    }

    public boolean removeTaskHandler() {
        int userRemoveTask = taskManagerView.showActualTaskListPage(taskManagerModel.getActualTaskList());
        if(userRemoveTask == 0) return false;
        else if (userRemoveTask > 0) taskManagerModel.removeTask(userRemoveTask);
        return true;
    }

    public boolean watchListHandler() {
        int userChoice = taskManagerView.showActualTaskListPage(taskManagerModel.getActualTaskList());
        return userChoice != 0;
    }

    public boolean watchCalendarHandler() {
        String[] userCalendar = taskManagerModel.getCalendar(taskManagerView.getStartTime(), taskManagerView.getEndTime());
        int userChoice = taskManagerView.showCalendarPage(userCalendar);
        return userChoice != 0;
    }

}
