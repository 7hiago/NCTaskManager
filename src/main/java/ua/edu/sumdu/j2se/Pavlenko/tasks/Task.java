package ua.edu.sumdu.j2se.Pavlenko.tasks;

import java.util.Objects;

/**
 * Task - data about event that user should perform at a certain period of time.
 * Task has the following functions:
 *  - create new task;
 *  - change and return title;
 *  - change and return time;
 *  - change and return activity;
 *  - return interval of repeat;
 *  - return is repeated or not;
 *  - return next moment of the task execution.
 * @author Yevhenii Pavlenko
 */
public class Task implements Cloneable{
    private String title;
    private boolean isActive;
    private int time;
    private int startTime;
    private int endTime;
    private int intervalTime;

    /**
     * Constructor - create new not active and not repeated task
     * @param title task name (description)
     * @param time execution time of this task
     * @see #Task(String, int, int, int)
     * @throws IllegalArgumentException if time is negative
     */
    public Task(String title, int time) throws IllegalArgumentException {
        if(time < 0) throw new IllegalArgumentException("Timestamps should not be negative");
        setTitle(title);
        setTime(time);
        setActive(false);
    }

    /**
     * Constructor - create new not active and repeated task
     * @param title task name (description)
     * @param start start execution time of this task
     * @param end end execution time of this task
     * @param interval time interval at which the task repeats execution
     * @see #Task(String, int)
     * @throws IllegalArgumentException if start time or end time or interval time is negative
     */
    public Task (String title, int start, int end, int interval) throws IllegalArgumentException {
        if(start < 0 || end < 0) throw new IllegalArgumentException("Timestamps should not be negative");
        if(interval < 0) throw new IllegalArgumentException("Task repetition interval must be greater than zero");
        setTitle(title);
        setTime(start, end, interval);
        setActive(false);
    }

    /**
     * Method for getting task name
     * @return return task name (description)
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method for setting/changing task name
     * @param title task name (description)
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method for checking a task for an active state
     * @return return task state: true - active, false - not active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Method for setting the activity state of a task
     * @param active value of task state: true - active, false - not active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Method for getting execution time of this task
     * @return return execution time of this task when it is not repeated,
     *         or start execution time of this task when it is repeated
     */
    public int getTime() {
        if(isRepeated()) return getStartTime();
        return time;
    }

    /**
     * Method for setting/changing execution time of this task when it is not repeated,
     * and setting it not repeated when task is repeated
     * @param time execution time of this task
     * @see #setTime(int, int, int) 
     */
    public void setTime(int time) {
        intervalTime = 0;
        this.time = time;
    }

    /**
     * Method for getting start execution time of this task
     * @return return start execution time of this task when it is repeated,
     *         or execution time of this task when it is not repeated
     */
    public int getStartTime() {
        if(!isRepeated()) return getTime();
        return startTime;
    }

    /**
     * Method for getting end execution time of this task
     * @return return end execution time of this task when it is repeated,
     *         or execution time of this task when it is not repeated
     */
    public int getEndTime() {
        if(!isRepeated()) return getTime();
        return endTime;
    }

    /**
     * Method for getting time interval at which the task repeats execution
     * @return return time interval at which the task repeats execution when it is repeated,
     *         or 0 when it is not repeated
     */
    public int getRepeatInterval() {
        if(!isRepeated()) return 0;
        return intervalTime;
    }

    /**
     * Method for setting/changing start, end execution time of this task when it is repeated,
     * and setting time interval at which the task repeats execution
     * @param start start execution time of this task
     * @param end end execution time of this task
     * @param interval time interval at which the task repeats execution
     * @see #setTime(int)
     */
    public void setTime (int start, int end, int interval) {
        this.startTime = start;
        this.endTime = end;
        this.intervalTime = interval;
    }

    /**
     * Method for checking the repeatability status of a task
     * @return return task status: true - repeated, false - not repeated
     */
    public boolean isRepeated() {
        return intervalTime > 0;
    }

    /**
     * Method for finding next moment of the task execution
     * @param current start time of finding the next moment of the task execution
     * @return return time next moment of the task execution,
     *                and -1 when task is not active or:
     *                   - current is bigger then execution time of this task when it is not repeated;
     *                   - current is bigger then end execution time of this task when task is repeated.
     */
    public int nextTimeAfter(int current) {
        if(!isActive()) return -1;
        if(!isRepeated()){
            if(current < getStartTime()) return getStartTime();
        }
        if(isRepeated()) {
            if (current < getEndTime()) {
                int temp = getStartTime();
                do {
                    if (temp <= current) temp += getRepeatInterval();
                } while (temp <= current);
                if (temp < getEndTime()) return temp;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", isActive=" + isActive +
                ", time=" + time +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", intervalTime=" + intervalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isActive() == task.isActive() && getTime() == task.getTime() &&
                getStartTime() == task.getStartTime() && getEndTime() == task.getEndTime()
                && intervalTime == task.intervalTime && getTitle().equals(task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), getTime(), getStartTime(), getEndTime(), intervalTime);
    }

    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
