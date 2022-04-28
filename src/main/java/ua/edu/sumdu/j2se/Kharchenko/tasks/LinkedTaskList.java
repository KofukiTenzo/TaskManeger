package ua.edu.sumdu.j2se.kharchenko.tasks;

public class LinkedTaskList extends AbstractTaskList{
    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;


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

    public void add(Task task){
        final Node<Task> l = tail;
        final Node<Task> newNode = new Node<>(l, task, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    public boolean remove(Task task){
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

    public Task getTask(int index){
        if(!rangeCheck(index))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

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

    private boolean rangeCheck(int index){
        return index >= 0 && index < size;
    }

    public int size(){
        return size;
    }
}
