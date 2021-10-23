package ua.edu.sumdu.j2se.Pavlenko.tasks;

/**
 * ArrayTaskList - task list, based on an array and designed to store user tasks.
 * List has the following functions:
 *  - add tasks;
 *  - remove tasks;
 *  - return size of tasks list;
 *  - return single task from list;
 *  - return subset of tasks for a certain interval.
 * @author Yevhenii Pavlenko
 */
public class ArrayTaskList {
    private Task[] taskList = new Task[10];
    private int size;
    private static final double MULTIPLIER = 1.4;

    /**
     * Method for adding new task to task list
     * @param task - task for adding to list
     * @throws IllegalArgumentException if task is null
     */
    public void add(Task task) throws IllegalArgumentException {
        if(task == null) throw new IllegalArgumentException("Task should not be a null");
        if (size > taskList.length) {
            Task[] temp = new Task[getNewArraySize(taskList.length)];
            System.arraycopy(taskList, 0, temp, 0, taskList.length);
            taskList = temp;
        }
        taskList[size] = task;
        size++;
    }

    private int getNewArraySize(int currentSize){
        return (int)(currentSize * MULTIPLIER);
    }

    /**
     * Method for remove task from task list
     * @param task - task for removing from list
     * @return return: - true, when task was exist in list,
     *                 - false, when task was not exist in list
     */
    public boolean remove(Task task) {
        int taskPos = indexOf(task);
        if(isTaskPresent(task)){
            for (int i = 0, j = 0; i < taskList.length; i++, j++) {
                if (taskPos == i) {
                    j--;
                    continue;
                }
                taskList[j] = taskList[i];
            }
            size--;
            return true;
        } else {
            return false;
        }
    }

    private int indexOf(Task task) {
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i].equals(task)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isTaskPresent(Task task) {
        return indexOf(task) >= 0;
    }

    /**
     * Method for getting amount of task in task list
     * @return return amount of task in task list
     */
    public int size() {
        return size;
    }

    /**
     * Method for getting task in specified location in task list
     * @return return task from task list
     * @throws IndexOutOfBoundsException if index is out of range for the list
     */
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index is out of range for the list");
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
            if(task != null && task.getStartTime() > from && task.nextTimeAfter(from) != -1 && task.getEndTime() <= to) {
                list.add(task);
            }
        }
        return list;
    }
}
