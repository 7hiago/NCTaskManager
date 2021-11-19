package ua.edu.sumdu.j2se.Pavlenko.tasks;

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
 * @author Yevhenii Pavlenko
 */
public abstract class AbstractTaskList implements Iterable<Task> {

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
     * Method for getting inherit class type for abstract class
     * @return return inherit class type
     */
    public abstract ListTypes.types getClassType();

    /**
     * Method for iterating list
     * @return return next item from list if it has next item
     */
    public abstract Iterator<Task> iterator();

    /**
     * Method for getting a subset of tasks that are scheduled to run at least once after time "from" and no later than "to"
     * @param from start time to find subset of tasks
     * @param to end time to find subset of tasks
     * @return return subset of tasks that are corresponding to conditions
     */
    public final AbstractTaskList incoming (int from, int to) {
        AbstractTaskList list = TaskListFactory.createTaskList(getClassType());
        this.getStream().filter(task -> task != null && task.nextTimeAfter(from) != -1 && task.getEndTime() <= to).forEach(list::add);
        return list;
    }

    /**
     * Method for getting a sequence of elements supporting sequential and parallel aggregate operations with collections
     * @return return stream of task
     */
    public abstract Stream<Task> getStream();
}
