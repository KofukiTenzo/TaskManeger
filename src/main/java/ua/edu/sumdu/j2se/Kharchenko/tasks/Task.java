package ua.edu.sumdu.j2se.Kharchenko.tasks;

public class Task {
    private static String title;
    private static int time;
    private static int start;
    private static int end;
    private static int interval;
    private static boolean active;
    private static boolean repeated;

    public Task(String title, int time) {
        Task.title = title;
        Task.time = time;
    }

    public Task(String title, int start, int end, int interval) {
        Task.title = title;
        Task.start = start;
        Task.end = end;
        Task.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Task.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        Task.active = active;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public int getTime() {
        if (this.isRepeated()) return start;
        else return time;
    }

    public void setTime(int time) {
        Task.time = time;
        if (this.isRepeated()) repeated = false;
    }

    public int getStartTime() {
        if (this.isRepeated()) return start;
        else return time;
    }

    public int getEndTime() {
        if (this.isRepeated()) return end;
        else return time;
    }

    public int getRepeatInterval() {
        if (this.isRepeated()) return interval;
        else return 0;
    }

    public void setTime(int start, int end, int interval) {
        if (!this.isRepeated()) {
            Task.start = start;
            Task.end = end;
            Task.interval = interval;
        }
    }

    public int nextTimeAfter(int current){
        if (this.isRepeated()) {
            start = current;
            return current;
        } else return -1;
    }
}
