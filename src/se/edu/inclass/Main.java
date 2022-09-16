package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        System.out.println("All data:");
//        printData(tasksData);
//        printDataUsingStream(tasksData);

//        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);
//        printDeadlinesUsingStream(tasksData);

        printDeadlinesUsingStream(tasksData);
        ArrayList<Task> filteredList = filterTaskByString(tasksData, "11");
        System.out.println("Data with Stirng 11:");
        printData(filteredList);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines (using streams): " + countDeadlinesUsingStream(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks){
        System.out.println("Counting using streams");
        int count = (int) tasks.stream()
                .filter(t -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using loop");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStream(ArrayList<Task> tasks){
        System.out.println("Printing data using streams");
        tasks.stream()
                .forEach(System.out::println);

    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadline using loop");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks){
        System.out.println("Printing sorted deadlines");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a,b) -> {
                    //System.out.println("Lemme check");
                    return a.getDescription().compareToIgnoreCase(b.getDescription());
                })
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String filterString){
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());

        return  filteredList;
    }
}