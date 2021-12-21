package ua.edu.sumdu.j2se.pavlenko.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * ArrayTaskList - task list, based on an array and designed to store user tasks.
 * List has the following functions:
 *  - add tasks;
 *  - remove tasks;
 *  - return size of tasks list;
 *  - return single task from list;
 *  - return subset of tasks for a certain interval.
 * @author Yevhenii pavlenko
 */
public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    private Task[] taskList = new Task[10];
    private int size;
    private static final double MULTIPLIER = 1.4;

    /**
     * Method for adding new task to task list
     * @param task task for adding to list
     * @throws IllegalArgumentException if task is null
     */
    @Override
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
     * @param task task for removing from list
     * @return return: - true, when task was exist in list,
     *                 - false, when task was not exist in list
     */
    @Override
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
            if (taskList[i] != null && taskList[i].equals(task)) {
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
        return taskList[index];
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < size;
            }

            @Override
            public Task next() {
                return taskList[iteratorIndex++];
            }

            @Override
            public void remove() {
                if(iteratorIndex == 0 ) throw new IllegalStateException();
                ArrayTaskList.this.remove(taskList[--iteratorIndex]);
            }
        };
    }

    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(taskList);
    }

    @Override
    public String toString() {
        Iterator<Task> taskIterator = this.iterator();
        StringBuilder string = new StringBuilder("ArrayTaskList{");
        while (taskIterator.hasNext()) {
            string.append(taskIterator.next().toString()).append(", ");
        }
        return string.append("size=").append(size).append('}').toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return Arrays.equals(taskList, that.taskList);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taskList);
    }

    @Override
    public ArrayTaskList clone() {
        try {
            ArrayTaskList newList = (ArrayTaskList) super.clone();
            newList.taskList = taskList.clone();
            return newList;
        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }


}
