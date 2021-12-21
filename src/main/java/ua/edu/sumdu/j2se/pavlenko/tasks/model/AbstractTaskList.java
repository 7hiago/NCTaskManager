package ua.edu.sumdu.j2se.pavlenko.tasks.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * AbstractTaskList - abstract task list is a template for different realization of lists designed to store user tasks.
 * List has the following main functions:
 *  - add tasks;
 *  - remove tasks;
 *  - return size of tasks list;
 *  - return single task from list;
 *  - return subset of tasks for a certain interval.
 * @author Yevhenii pavlenko
 */
public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    /**
     * Method for adding new task to task list
     * @param task - task for adding to list
     */
    public abstract void add(Task task);

    /**
     * Method for remove task from task list
     * @param task task for removing from list
     * @return return: - true, when task was exist in list,
     *                 - false, when task was not exist in list
     */
    public abstract boolean remove(Task task);

    /**
     * Method for getting amount of task in task list
     * @return return amount of task in task list
     */
    public abstract int size();

    /**
     * Method for getting task in specified location in task list
     * @return return task from task list
     */
    public abstract Task getTask(int index);

    /**
     * Method for iterating list
     * @return return next item from list if it has next item
     */
    public abstract Iterator<Task> iterator();

    /**
     * Method for getting a sequence of elements supporting sequential and parallel aggregate operations with collections
     * @return return stream of task
     */
    public abstract Stream<Task> getStream();
}
