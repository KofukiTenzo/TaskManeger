package ua.edu.sumdu.j2se.kharchenko.tasks;

public interface TaskListFactory {
    static AbstractTaskList createTaskList(ListTypes.types type) {
        switch (type) {
            case ARRAY: return new ArrayTaskList();
            case LINKED: return new LinkedTaskList();
            default: throw new IllegalArgumentException();
        }
    }
}
