package ua.edu.sumdu.j2se.Pavlenko.tasks;

public class Task {
    private String title;
    private boolean isActive;
    private int time;
    private int startTime;
    private int endTime;
    private int intervalTime;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        setActive(false);
    }
    public Task (String title, int start, int end, int interval) {
        this.title = title;
        setTime(start, end, interval);
        setActive(false);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTime() {
        if(isRepeated()) return getStartTime();
        return time;
    }

    public void setTime(int time) {
        intervalTime = 0;
        this.time = time;
    }

    public int getStartTime() {
        if(!isRepeated()) return getTime();
        return startTime;
    }

    public int getEndTime() {
        if(!isRepeated()) return getTime();
        return endTime;
    }

    public int getRepeatInterval() {
        if(!isRepeated()) return 0;
        return intervalTime;
    }

    public void setTime (int start, int end, int interval) {
        this.startTime = start;
        this.endTime = end;
        this.intervalTime = interval;
    }

    public boolean isRepeated() {
        return intervalTime > 0;
    }

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
}
