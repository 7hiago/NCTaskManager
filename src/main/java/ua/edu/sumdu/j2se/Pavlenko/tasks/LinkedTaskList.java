package ua.edu.sumdu.j2se.Pavlenko.tasks;

import java.util.Iterator;

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
public class LinkedTaskList extends AbstractTaskList{

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
     * @param task task for adding to list
     * @throws IllegalArgumentException if task is null
     */
    @Override
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
     * @param task task for removing from list
     * @return return: - true, when task was exist in list,
     *                 - false, when task was not exist in list
     */
    @Override
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
    @Override
    public int size() {
        return size;
    }

    /**
     * Method for getting task in specified location in task list
     * @return return task from task list
     * @throws IndexOutOfBoundsException if index is out of range for the list
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException("Index is out of range for the list");
        TaskListItem temp = headItem;
        if(index < size/2){
            for (int i = 0; i < size; i++){
                if(i == index) {
                    break;
                }
                temp = temp.next;
            }
        } else {
            temp = lastItem;
            for (int i = size - 1; i >= 0; i--) {
                if (i == index) {
                    break;
                }
                temp = temp.prev;
            }
        }
        return temp.task;
    }

    @Override
    public AbstractTaskList getExtensionInstance() {
        return new LinkedTaskList();
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            TaskListItem iteratorTemp = new TaskListItem(null, null, headItem);
            @Override
            public boolean hasNext() {
                return iteratorTemp.next != null;
            }

            @Override
            public Task next() {
                iteratorTemp = iteratorTemp.next;
                return iteratorTemp.task;
            }
        };
    }

    @Override
    public String toString() {
        Iterator<Task> taskIterator = this.iterator();
        String string = "LinkedTaskList{";
        while (taskIterator.hasNext()) {
            string = string + taskIterator.next().toString() + ", ";
        }
        return string + "size=" + size + '}';
    }
}
