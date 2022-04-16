package ua.edu.sumdu.j2se.Kharchenko.tasks;

public class Main {

	public static void main(String[] args) {
		Task task0 = new Task("A", 42);
		Task task1 = new Task("B", 42);
		Task task2 = new Task("C", 42);
		Task task3 = new Task("D", 42);
		Task task4 = new Task("E", 42);
		ArrayTaskList arrayTaskList = new ArrayTaskList();
		arrayTaskList.add(task0);
		arrayTaskList.add(task1);
		arrayTaskList.add(task2);
		arrayTaskList.add(task3);
		arrayTaskList.add(task4);
		System.out.println("Task 0 - " + arrayTaskList.getTask(0));
		System.out.println("Task 1 - " + arrayTaskList.getTask(1));
		System.out.println("Task 2 - " + arrayTaskList.getTask(2));
		System.out.println("Task 3 - " + arrayTaskList.getTask(3));
		System.out.println("Task 4 - " + arrayTaskList.getTask(4) + "\n");

//		arrayTaskList.remove(task4);
//
//		for (int i = 0; i < arrayTaskList.size(); i++) {
//			System.out.printf("Task %d - " + arrayTaskList.getTask(i) + "\n", i);
//		}
//		System.out.println(arrayTaskList.size());

//		arrayTaskList.remove(task0);
//
//		for (int i = 0; i < arrayTaskList.size(); i++) {
//			System.out.printf("Task %d - " + arrayTaskList.getTask(i) + "\n", i);
//		}
//		System.out.println(arrayTaskList.size());

		arrayTaskList.remove(task0);
		arrayTaskList.remove(task1);
		for (int i = 0; i < arrayTaskList.size(); i++) {
			System.out.printf("Task %d - " + arrayTaskList.getTask(i) + "\n", i);
		}
		System.out.println(arrayTaskList.size());

		arrayTaskList.add(task1);
		arrayTaskList.add(task0);
		for (int i = 0; i < arrayTaskList.size(); i++) {
			System.out.printf("Task %d - " + arrayTaskList.getTask(i) + "\n", i);
		}
		System.out.println(arrayTaskList.size());
	}
}
