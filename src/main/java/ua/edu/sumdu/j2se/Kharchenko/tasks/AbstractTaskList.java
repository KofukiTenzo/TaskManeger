package ua.edu.sumdu.j2se.kharchenko.tasks;

import static ua.edu.sumdu.j2se.kharchenko.tasks.TaskListFactory.createTaskList;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable{
    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    public abstract int size();

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public abstract ListTypes.types getType();

    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList list = createTaskList(getType());

        for (int i = 0; i < size();i++) {
            Task task = getTask(i);
            if (task.isActive()) {
                if (task.getStartTime() > from && task.getEndTime() <= to) {
                    list.add(task);
                }
            }
        }

        return list;
    }
}
