package ua.edu.sumdu.j2se.kharchenko.tasks;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable{
    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    public abstract int size();

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public abstract ListTypes.types getType();

    public Stream<Task> getStream() {
        Task[] elements = new Task[size()];
        for (int i = 0;i < size();i++) {
            elements[i] = getTask(i);
        }
        return Arrays.stream(elements);
    }
}
