package ua.edu.sumdu.j2se.Pavlenko.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        return type == ListTypes.types.ARRAY ? new ArrayTaskList() : new LinkedTaskList();
    }
}
