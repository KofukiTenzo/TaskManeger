package ua.edu.sumdu.j2se.Kharchenko.tasks;

public class ArrayTaskList {
    private int size;
    private int capacity;
    private Task[] elements;

    public ArrayTaskList() {
        capacity = 10;
        size = 0;
        elements = new Task[capacity];
    }

    public void add(Task task) {
        if (task == null){
            try{
                throw new NullPointerException("Задача не может быть пустой! " + "Задача не добавлена");
            }catch(NullPointerException nullPointerException){
                System.out.println(nullPointerException.getMessage());
            }
        }

        try{
            if (size == capacity) {
                grow();
            }
            elements[size] = task;
            size++;
        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
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
        if (task == null){
            try{
                throw new IllegalArgumentException("Задача не была найдена");
            }catch (IllegalArgumentException illegalArgumentException){
                System.out.println(illegalArgumentException.getMessage());
            }
            return false;
        }else {
            Task[] copy = new Task[capacity];
            boolean chek = false;
            for (int i = 0; i < size; i++) {
                if (getTask(i).equals(task)) {
                    System.arraycopy(elements, 0, copy, 0, i);
                    System.arraycopy(elements, i + 1, copy, i, size - i - 1);
                    chek = true;
                }
            }
            if (chek){
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
        if (index < 0 || index >= this.size){
            try{
                throw new IndexOutOfBoundsException("Неверный индекс задачи");
            }catch(IndexOutOfBoundsException indexOutOfBoundsException){
                System.out.println(indexOutOfBoundsException.getMessage());
            }
            return null;
        }else return elements[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incoming = new ArrayTaskList();
        for (int i = 0; i < size(); i++) {
            if (getTask(i).nextTimeAfter(from) >= 0) {
                if (getTask(i).nextTimeAfter(from) <= to)
                    incoming.add(getTask(i));
            }
        }
        return incoming;
    }
}
