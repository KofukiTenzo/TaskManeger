package ua.edu.sumdu.j2se.kharchenko.tasks;

import java.util.Arrays;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Stream<Task> taskStream = Arrays.stream(StreamSupport.stream(tasks.spliterator(), false).toArray(Task[]::new));
        ArrayTaskList list = new ArrayTaskList();
        taskStream.filter(task -> task.nextTimeAfter(start) != null && task.nextTimeAfter(start).compareTo(end) <= 0)
                .forEach(list::add);
        return list;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> setSortedMap = new TreeMap<>();
        for (Task task : tasks
        ) {
            LocalDateTime nextTime = task.nextTimeAfter(start);
            while (nextTime != null && nextTime.compareTo(end) <= 0) {
                if (!setSortedMap.containsKey(nextTime)) {
                    Set<Task> set = new HashSet<>();
                    set.add(task);
                    setSortedMap.put(nextTime, set);
                } else {
                    setSortedMap.get(nextTime).add(task);
                }
                nextTime = nextTime.plusSeconds(task.getRepeatInterval());
            }
        }
        return setSortedMap;
    }
}
