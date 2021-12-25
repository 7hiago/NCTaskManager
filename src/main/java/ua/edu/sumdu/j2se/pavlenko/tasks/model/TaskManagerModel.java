package ua.edu.sumdu.j2se.pavlenko.tasks.model;

import ua.edu.sumdu.j2se.pavlenko.tasks.utils.Adapter;
import ua.edu.sumdu.j2se.pavlenko.tasks.utils.TaskIO;
import ua.edu.sumdu.j2se.pavlenko.tasks.utils.Tasks;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class TaskManagerModel {
    private LinkedTaskList taskList = new LinkedTaskList();

    public void createNotRepeatedTask(String title, String time) {
        Task newTask = new Task(title, Adapter.timeAdapter(time));
        taskList.add(newTask);
    }

    public void createRepeatedTask(String title, String startTime, String endTime, String interval) {
        Task newTask = new Task(title, Adapter.timeAdapter(startTime), Adapter.timeAdapter(endTime), Integer.parseInt(interval));
        taskList.add(newTask);
    }

    public String[] getActualTaskList() {
        String[] list = new String[taskList.size()];
        int i = 0;
        for (Task task : taskList) {
            StringBuilder item = new StringBuilder();
            item.append(task.getTitle());
            if(task.isActive()) {
                item.append(", active");
            } else {
                item.append(", not active");
            }
            item.append(", time: ");
            if(task.isRepeated()) {
                item.append("from ").append(task.getStartTime()).append(" to ").append(task.getEndTime())
                        .append(" with period: ").append(task.getRepeatInterval());
            } else {
                item.append(task.getTime());
            }
            list[i++] = item.toString();
        }
        return list;
    }

    public void changeTaskTitle(int taskIndex, String newValue) {
        taskList.getTask(taskIndex - 1).setTitle(newValue);
    }

    public void changeTaskStartTime(int taskIndex, String newValue) {
        Task task = taskList.getTask(taskIndex - 1);
        if(task.isRepeated()) {
            task.setTime(Adapter.timeAdapter(newValue), task.getEndTime(), task.getRepeatInterval());
        } else {
            task.setTime(Adapter.timeAdapter(newValue));
        }
    }

    public void changeTaskEndTime(int taskIndex, String newValue) {
        Task task = taskList.getTask(taskIndex - 1);
        task.setTime(task.getStartTime(), Adapter.timeAdapter(newValue), task.getRepeatInterval());
    }

    public void changeTaskInterval(int taskIndex, String newValue) {
        Task task = taskList.getTask(taskIndex - 1);
        task.setTime(task.getStartTime(), task.getEndTime(), Integer.parseInt(newValue));
    }

    public void changeTaskActivation(int taskIndex, String newValue) {
        taskList.getTask(taskIndex - 1).setActive(Integer.parseInt(newValue) > 0);
    }

    public void removeTask(int taskIndex) {
        taskList.remove(taskList.getTask(taskIndex - 1));
    }

    public String[] getCalendar(String startDate, String endDate) {
        SortedMap<LocalDateTime, Set<Task>> calendarList = Tasks.calendar(taskList, Adapter.timeAdapter(startDate), Adapter.timeAdapter(endDate));
        String[] list = new String[calendarList.size()];
        int i = 0;
        for (Map.Entry<LocalDateTime, Set<Task>> taskListItem : calendarList.entrySet()) {
            StringBuilder item = new StringBuilder();
            item.append(taskListItem.getKey()).append(": "); // .toLocalDate() 5.toLocalTime()
            HashSet<Task> set = (HashSet<Task>) taskListItem.getValue();
            for(Task task : set) {
                item.append(task.getTitle()).append(", ");
            }
            list[i++] = item.toString();
        }
        return list;
    }

    public void loadDataFromFile() throws IOException {
        File file = new File("add/taskList.txt");
        if(!file.createNewFile()) {
            TaskIO.readBinary(taskList, new File("add/taskList.txt"));
        }
    }

    public void saveDataToFile() throws IOException {
        TaskIO.writeBinary(taskList, new File("add/taskList.txt"));
    }

    public String getStartRunningTasks() {
        LocalDateTime timeNow = LocalDateTime.now().withNano(0);
        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, timeNow.minusSeconds(1), timeNow);
        StringBuilder item = new StringBuilder();
        if(calendar.size() != 0) {
            if (calendar.firstKey().isEqual(timeNow)) {
                item.append("Start ");
                HashSet<Task> set = (HashSet<Task>) calendar.get(calendar.firstKey());
                for (Task task : set) {
                    item.append(task.getTitle()).append(", ");
                }
            }
        }
        return item.toString();
    }
}
