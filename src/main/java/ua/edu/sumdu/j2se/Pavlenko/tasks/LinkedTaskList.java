package ua.edu.sumdu.j2se.Pavlenko.tasks;

/**
 * LinkedTaskList - task list, based on doubly linked list and designed to store user tasks.
 * List has the following functions:
 *  - add tasks;
 *  - remove tasks;
 *  - return size of tasks list;
 *  - return single task from list;
 *  - return subset of tasks for a certain interval.
 * @author Yevhenii Pavlenko
 */
public class LinkedTaskList {

    private class TaskListItem {
        private Task task;
        private TaskListItem prev;
        private TaskListItem next;

        /**
         * Constructor - create new item in linked task list
         * @param task - data that belongs to this item
         * @param prev - previous item reference in list relative to this item
         * @param next - next item reference in list relative to this item
         */
        public TaskListItem (Task task, TaskListItem prev, TaskListItem next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }
    }

    private TaskListItem headItem;
    private TaskListItem lastItem;
    private int size;

    /**
     * Method for adding new task to task list
     * @param task - task for adding to list
     */
    public void add(Task task) throws IllegalArgumentException{
        if(task == null) throw new IllegalArgumentException("Task should not be a null");
        TaskListItem newItem = new TaskListItem(task, lastItem, null);
        if(headItem == null) {
            headItem = lastItem = newItem;
        } else {
            TaskListItem temp = lastItem;
            lastItem = newItem;
            temp.next = lastItem;
        }
        size++;
    }

    /**
     * Method for remove task from task list
     * @param task - task for removing from list
     * @return return: - true, when task was exist in list,
     *                 - false, when task was not exist in list
     */
    public boolean remove(Task task) {
        TaskListItem temp = headItem;
        for (int i = 0; i < size; i++){
            if(task.equals(temp.task)) {
                if(temp.prev == null){
                    headItem = temp.next;
                    headItem.prev = null;
                }
                else if (temp.next == null) {
                    lastItem = temp.prev;
                    lastItem.next = null;
                }
                else {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                }
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
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
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index is out of range for the list");
        TaskListItem temp = headItem;
        for (int i = 0; i < size; i++){
            if(i == index) {
                break;
            }
            temp = temp.next;
        }
        return temp.task;
    }

    /**
     * Method for getting a subset of tasks that are scheduled to run at least once after time "from" and no later than "to"
     * @param from - start time to find subset of tasks
     * @param to - end time to find subset of tasks
     * @return return subset of tasks that are corresponding to conditions
     */
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList list = new LinkedTaskList();
        TaskListItem temp = headItem;
        for (int i = 0; i < size; i++) {
            if(temp.task != null && temp.task.getStartTime() > from && temp.task.nextTimeAfter(from) != -1 && temp.task.getEndTime() <= to) {
                list.add(temp.task);
            }
            temp = temp.next;
        }
        return list;
    }
}
