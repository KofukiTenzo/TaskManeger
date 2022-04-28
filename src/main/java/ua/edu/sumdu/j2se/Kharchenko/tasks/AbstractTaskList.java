package ua.edu.sumdu.j2se.kharchenko.tasks;

public abstract class AbstractTaskList {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract Task getTask(int index);
    public abstract int size();
    public AbstractTaskList incoming(int from, int to) {

        AbstractTaskList list;
        if (this.getClass().toString().equals("ArrayTaskList")) {
            list = new ArrayTaskList();
        } else {
            list = new LinkedTaskList();
        }
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
