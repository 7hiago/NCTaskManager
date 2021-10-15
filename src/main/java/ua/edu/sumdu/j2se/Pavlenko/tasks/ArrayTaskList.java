package ua.edu.sumdu.j2se.Pavlenko.tasks;

/**
 * ArrayTaskList class
 * @author Yevhenii Pavlenko
 */
public class ArrayTaskList {
    private Task[] taskList = new Task[]{};

    /**
     * Method for adding new task to task list
     * @param task - task for adding to list
     */
    public  void add(Task task) {
        Task[] temp = taskList.clone();
        taskList = new Task[temp.length + 1];
        System.arraycopy(temp, 0, taskList, 0, temp.length);
        taskList[taskList.length - 1] = task;
    }

    /**
     * Method for remove task from task list
     * @param task - task for removing from list
     * @return return: - true, when task was exist in list,
     *                 - false, when task was not exist in list
     */
    public boolean remove(Task task) {
        boolean isTaskPresent = false;
        int taskPos = 0;
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == task) {
                isTaskPresent = true;
                taskPos = i;
                break;
            }
        }
        if(isTaskPresent){
            Task[] temp = taskList.clone();
            taskList = new Task[temp.length - 1];
            for (int i = 0, j = 0; i < temp.length; i++, j++) {
                if (taskPos == i) {
                    j--;
                    continue;
                }
                taskList[j] = temp[i];
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method for getting amount of task in task list
     * @return return amount of task in task list
     */
    public int size() {
        return taskList.length;
    }

    /**
     * Method for getting task in specified location in task list
     * @return return task from task list
     */
    public Task getTask(int index) {
        return taskList[index];
    }

    /**
     * Method for getting a subset of tasks that are scheduled to run at least once after time "from" and no later than "to"
     * @param from - start time to find subset of tasks
     * @param to - end time to find subset of tasks
     * @return return subset of tasks that are corresponding to conditions
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList list = new ArrayTaskList();
        for (Task task : taskList) {
            if(task.getStartTime() > from && task.nextTimeAfter(from) != -1 && task.getEndTime() <= to) {
                list.add(task);
            }
        }
        return list;
    }
}
