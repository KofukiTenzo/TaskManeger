package ua.edu.sumdu.j2se.kharchenko.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, LocalDateTime time) {
        if (title == null || time == null)
            throw new IllegalArgumentException("Время введено некорректно!");

        this.title = title;
        this.time = time;
        this.repeated = false;
    }

    public Task() {
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null || interval < 0 || title == null)
            throw new IllegalArgumentException("Время введено не корректно!");

        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) throw new IllegalArgumentException();
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

    public LocalDateTime getTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException();
        }
        this.time = time;
        this.repeated = false;
    }

    public LocalDateTime getStartTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    public LocalDateTime getEndTime() {
        if (repeated) {
            return end;
        } else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        } else {
            return 0;
        }
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null || interval < 0) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (active) {
            if (repeated) {
                if (current.isBefore(start)) {
                    return start;
                } else {
                    LocalDateTime temp;
                    temp = start;
                    while (current.isAfter(temp) || current.isEqual(temp)) {
                        temp = temp.plusSeconds(interval);
                    }
                    if (temp.isBefore(end) || temp.isEqual(end)) {
                        return temp;
                    } else {
                        return null;
                    }
                }
            } else {
                if (current.isBefore(time)) {
                    return time;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return time == task.time && start == task.start && end == task.end && interval == task.interval
                && repeated == task.repeated && active == task.active && title.equals(task.title);
    }

    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, repeated, active);
    }

    public String toString() {
        return "Task:" +
                "\n  title\'" + title + '\'' +
                "\n  time: " + time +
                "\n  start: " + start +
                "\n  end: " + end +
                "\n  interval: " + interval +
                "\n  repeated: " + repeated +
                "\n  active: " + active;
    }

    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
