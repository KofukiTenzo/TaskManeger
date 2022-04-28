package ua.edu.sumdu.j2se.kharchenko.tasks;

import java.util.*;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    private int size;

    private int capacity;

    private Task[] elements;

    public ArrayTaskList() {
        capacity = 10;
        size = 0;
        elements = new Task[capacity];
    }

    public void add(Task task) {
        if (task == null) {
            try {
                throw new NullPointerException("Задача не может быть пустой! " + "Задача не добавлена");
            } catch (NullPointerException nullPointerException) {
                System.out.println(nullPointerException.getMessage());
            }
        }

        try {
            if (size == capacity) {
                grow();
            }
            elements[size] = task;
            size++;
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            grow();
        }
    }

    private void grow() {
        capacity = (int) (capacity * 1.5);
        Task[] newElements = new Task[capacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    public boolean remove(Task task) {
        if (task == null) {
            try {
                throw new IllegalArgumentException("Задача не была найдена");
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
            return false;
        } else {
            Task[] copy = new Task[capacity];
            boolean chek = false;
            for (int i = 0; i < size; i++) {
                if (getTask(i).equals(task)) {
                    System.arraycopy(elements, 0, copy, 0, i);
                    System.arraycopy(elements, i + 1, copy, i, size - i - 1);
                    chek = true;
                }
            }
            if (chek) {
                size--;
                elements = copy;
            }
            return chek;
        }
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= this.size) {
            try {
                throw new IndexOutOfBoundsException("Неверный индекс задачи");
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println(indexOutOfBoundsException.getMessage());
            }
            return null;
        } else return elements[index];
    }

    private void remove(int index) {
        if (index >= 0) {
            Task[] temp = elements;
            elements = new Task[temp.length];
            System.arraycopy(temp, 0, elements, 0, index);
            System.arraycopy(temp, index + 1, elements, index, temp.length - index - 1);
            size--;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    public String toString() {
        return "ArrayTaskList:" +
                "\n  task: " + Arrays.toString(elements) +
                "\n  size: " + size;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return size == that.size && Arrays.equals(elements, that.elements);
    }

    public Object clone() {
        try {
            ArrayTaskList result = (ArrayTaskList) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Iterator<Task> iterator() {
        return new Iterator<>() {
            int current;
            int prevElem = -1;

            public boolean hasNext() {
                return current != size;
            }

            public Task next() {
                try {
                    int i = current;
                    Task next = getTask(i);
                    prevElem = i;
                    current = i + 1;
                    return next;
                } catch (IndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
                if (prevElem < 0)
                    throw new IllegalStateException();

                try {
                    ArrayTaskList.this.remove(prevElem);
                    if (prevElem < current)
                        current--;
                    prevElem = -1;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            }

        };
    }
}
