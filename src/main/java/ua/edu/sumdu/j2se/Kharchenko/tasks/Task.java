package ua.edu.sumdu.j2se.Kharchenko.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.setTime(time);
        active = false;
    }

    public Task() {
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.setTime(start, end, interval);
        active = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        } else if (end >= 0 && interval >= 0) {
            this.start = start;
            this.end = end;
            this.interval = interval;
            repeated = true;
        } else {
            this.start = start;
            this.end = start;
            this.interval = 0;
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
