package ua.edu.sumdu.j2se.kharchenko.tasks;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    private Node<Task> head;
    private Node<Task> tail;
    private int size;


    private static class Node<Task> {
        Task value;
        Node<Task> next;
        Node<Task> prev;

        Node(Node<Task> prev, Task element, Node<Task> next) {
            this.value = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(Task task) {
        final Node<Task> l = tail;
        final Node<Task> newNode = new Node<>(l, task, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    public boolean remove(Task task) {
        if (task == null) {
            for (Node<Task> x = head; x != null; x = x.next) {
                if (x.value == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Task> x = head; x != null; x = x.next) {
                if (task.equals(x.value)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    Task unlink(Node<Task> x) {

        final Task element = x.value;
        final Node<Task> next = x.next;
        final Node<Task> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.value = null;
        size--;
        return element;
    }

    public Task getTask(int index) {
        if (!rangeCheck(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (index < (size >> 1)) {
            Node<Task> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x.value;
        } else {
            Node<Task> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x.value;
        }
    }

    private boolean rangeCheck(int index) {
        return index >= 0 && index < size;
    }

    public int size() {
        return size;
    }

    private Node rangeCheckByIndex(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void remove(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node prev = rangeCheckByIndex(index - 1);
            prev.next = prev.next.next;
            if (index == size - 1) {
                tail = prev;
            }
        }
        size--;
    }

    public String toString() {
        return "LinkedTaskList:" +
                "\n  first: " + head +
                "\n  last: " + tail +
                "\n  size: " + size;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LinkedTaskList))
            return false;
        Iterator<Task> it1 = iterator();
        Iterator<Task> it2 = ((LinkedTaskList) o).iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Task o1 = it1.next();
            Object o2 = it2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(it1.hasNext() || it2.hasNext());
    }

    public int hashCode() {
        int hashCode = 1;
        for (Task e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
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
                    LinkedTaskList.this.remove(prevElem);
                    if (prevElem < current)
                        current--;
                    prevElem = -1;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException();
                }
            }

        };
    }

    public Object clone() {
        LinkedTaskList clone = superClone();

        clone.head = clone.tail = null;
        clone.size = 0;

        for (Node x = head; x != null; x = x.next)
            clone.add((Task) x.value);

        return clone;
    }

    private LinkedTaskList superClone() {
        try {
            return (LinkedTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
