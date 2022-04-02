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
        this.setTime(time);
        active = false;
    }

    public Task() {
    }

    public Task(String title, int start, int end, int interval) {
        Task.title = title;
        this.setTime(start, end, interval);
        active = false;
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
        return interval > 0;
    }

    public int getTime() {
        return start;
    }

    public void setTime(int time) {
        repeated = false;
        this.setTime(time, time, 0);
    }

    public int getStartTime() {
        return start;
    }

    public int getEndTime() {
        if (end < 0) {
            end = 0;
            return 0;
        } else {
            return end;
        }
    }

    public int getRepeatInterval() {
        return (end >= 0 || end >= start) && repeated && interval > 0 ? interval : 0;
    }

    public void setTime(int start, int end, int interval) {
        if (start < 0) {
            Task.start = 0;
            Task.end = 0;
            Task.interval = 0;
        } else if (end >= 0 && interval >= 0) {
            Task.start = start;
            Task.end = end;
            Task.interval = interval;
            repeated = true;
        } else {
            Task.start = start;
            Task.end = start;
            Task.interval = 0;
        }

    }

    public int nextTimeAfter(int current) {
        if (this.isActive() && current < end && current <= end - interval) {
            if (current < this.getTime()) {
                return this.getTime();
            } else if ((current < start + interval || current == start) && this.isRepeated()) {
                return start + interval;
            } else if (current > end - interval && current < end && this.isRepeated()) {
                return end;
            } else {
                int num1 = (end - start) / interval;

                int i;
                for (int num2 = 1; num2 < num1; ++num2) {
                    i = start + interval * num2;
                    if (current == i) {
                        return i + interval;
                    }
                }

                for (i = start; i < end; i += interval) {
                    if (current >= i && current < i + interval && current < end && i + interval <= end || current < end - interval && current > end - interval * 2) {
                        return i + interval;
                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }
}
