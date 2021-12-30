package ua.edu.sumdu.j2se.pavlenko.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.pavlenko.tasks.utils.TaskIO;
import ua.edu.sumdu.j2se.pavlenko.tasks.utils.Tasks;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


public class TaskManagerModel {
    private static Logger logger = Logger.getLogger(TaskManagerModel.class);
    private AbstractTaskList taskList = new LinkedTaskList();
    private LocalDateTime lastNotificationTime = LocalDateTime.of(2000,1,1,0,0);

    public void createNotRepeatedTask(String title, LocalDateTime time) {
        logger.debug("invocation createNotRepeatedTask() method");
        Task newTask = new Task(title, time);
        newTask.setActive(true);
        taskList.add(newTask);
    }

    public void createRepeatedTask(String title, LocalDateTime startTime, LocalDateTime endTime, int interval) {
        logger.debug("invocation createRepeatedTask() method");
        Task newTask = new Task(title, startTime, endTime, interval);
        newTask.setActive(true);
        taskList.add(newTask);
    }

    public String[] getActualTaskList() {
        logger.debug("invocation getActualTaskList() method");
        String[] list = new String[taskList.size()];
        int i = 0;
        for (Task task : taskList) {
            list[i++] = task.toString();
        }
        return list;
    }

    public void changeTaskTitle(int taskIndex, String newValue) {
        logger.debug("invocation changeTaskTitle() method");
        taskList.getTask(taskIndex - 1).setTitle(newValue);
    }

    public void changeTaskStartTime(int taskIndex, LocalDateTime newValue) {
        logger.debug("invocation changeTaskStartTime() method");
        Task task = taskList.getTask(taskIndex - 1);
        if(task.isRepeated()) {
            task.setTime(newValue, task.getEndTime(), task.getRepeatInterval());
        } else {
            task.setTime(newValue);
        }
    }

    public void changeTaskEndTime(int taskIndex, LocalDateTime newValue) {
        logger.debug("invocation changeTaskEndTime() method");
        Task task = taskList.getTask(taskIndex - 1);
        task.setTime(task.getStartTime(), newValue, task.getRepeatInterval());
    }

    public void changeTaskInterval(int taskIndex, int newValue) {
        logger.debug("invocation changeTaskInterval() method");
        Task task = taskList.getTask(taskIndex - 1);
        task.setTime(task.getStartTime(), task.getEndTime(), newValue);
    }

    public void changeTaskActivation(int taskIndex, boolean newValue) {
        logger.debug("invocation changeTaskActivation() method");
        taskList.getTask(taskIndex - 1).setActive(newValue);
    }

    public void removeTask(int taskIndex) {
        logger.debug("invocation removeTask() method");
        taskList.remove(taskList.getTask(taskIndex - 1));
    }

    public String[] getCalendar(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("invocation getCalendar() method");
        SortedMap<LocalDateTime, Set<Task>> calendarList = Tasks.calendar(taskList, startDate, endDate);
        String[] list = new String[calendarList.size()];
        int i = 0;
        for (Map.Entry<LocalDateTime, Set<Task>> taskListItem : calendarList.entrySet()) {
            StringBuilder item = new StringBuilder();
            item.append(taskListItem.getKey().toLocalDate())
                    .append(" ")
                    .append(taskListItem.getKey().toLocalTime())
                    .append(" : ");
            HashSet<Task> set = (HashSet<Task>) taskListItem.getValue();
            int j = 0;
            for(Task task : set) {
                item.append(task.getTitle());
                j++;
                if(j < set.size())
                    item.append(", ");
            }
            list[i++] = item.toString();
        }
        return list;
    }

    public void loadDataFromFile() {
        logger.debug("invocation loadDataFromFile() method");
        try {
            File file = new File("add/taskList.txt");
            if (!file.exists()){
                new File("add/").mkdir();
            }
            if (!file.createNewFile()) {
                TaskIO.readBinary(taskList, new File("add/taskList.txt"));
            }
        } catch (EOFException exception) {
            logger.warn("Loading file is empty");
        } catch (IOException exception) {
            logger.error("Could not load data from file\n" + exception);
            logger.info("Stop executing TaskManager with status 1");
            System.exit(1);

        }
        logger.info("Load data successful completed");
    }

    public void saveDataToFile(){
        logger.debug("invocation saveDataToFile() method");
        try {
            TaskIO.writeBinary(taskList, new File("add/taskList.txt"));
        } catch (IOException exception) {
            logger.error("Could not save data to file\n" + exception);
            logger.info("Stop executing TaskManager with status 2");
            System.exit(2);
        }
        logger.info("Data saving successfully completed");
    }

    public String getStartRunningTasks() {
        LocalDateTime timeNow = LocalDateTime.now().withSecond(0).withNano(0);
        if(lastNotificationTime.isEqual(timeNow)) return "";
        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, timeNow.minusMinutes(1), timeNow);
        StringBuilder item = new StringBuilder();
        if(calendar.size() != 0) {
            if (calendar.firstKey().isEqual(timeNow)) {
                HashSet<Task> set = (HashSet<Task>) calendar.get(calendar.firstKey());
                int j = 0;
                for (Task task : set) {
                    item.append(task.getTitle());
                    j++;
                    if(j < set.size())
                        item.append(", ");
                }
            }
        }
        lastNotificationTime = timeNow;
        return item.toString();
    }
}
