package ua.edu.sumdu.j2se.Pavlenko.tasks;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {

    /**
     * Method for getting a subset of tasks that are scheduled to run at least once after time "from" and no later than "to"
     * @param start start time to find subset of tasks
     * @param end end time to find subset of tasks
     * @return return subset of tasks that are corresponding to conditions
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        return StreamSupport.stream(tasks.spliterator(), false)
                .filter(task -> task != null && task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0)
                .collect(Collectors.toList());
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> mapList = new TreeMap<>();

        for(Task task : Tasks.incoming(tasks, start, end)) {
            for(LocalDateTime date = task.nextTimeAfter(start);  date.compareTo(end) <= 0; date = task.nextTimeAfter(date)) {
                if (mapList.get(date) != null) {
                    mapList.get(date).add(task);
                } else {
                    HashSet<Task> values = new HashSet<>();
                    values.add(task);
                    mapList.put(date, values);
                }
            }
        }
        return mapList;
    }
}
