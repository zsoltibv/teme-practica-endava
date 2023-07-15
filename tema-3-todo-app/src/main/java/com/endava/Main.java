package com.endava;

import com.endava.model.Task;
import com.endava.service.TaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static void displayAllTasks(TaskService taskService) {
        System.out.println("All Tasks: ");
        List<Task> tasks = taskService.getTasks();
        tasks.forEach(System.out::println);
    }

    private static void findTask(TaskService taskService, int id) {
        taskService.findTask(id)
                .ifPresentOrElse(
                        task -> System.out.println("Task found: " + task),
                        () -> System.out.println("Task " + id + " not found"));
    }

    public static void addTask(TaskService taskService, Task task){
        System.out.println("Adding " + task);
        taskService.addTask(task);
    }

    public static void deleteTask(TaskService taskService, int id){
        System.out.println("Deleting task with id: " + id);
        taskService.removeTask(id);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);

        TaskService taskService = context.getBean("taskService", TaskService.class);

        displayAllTasks(taskService);
        findTask(taskService, 5);
        findTask(taskService, 2);
        addTask(taskService, new Task(4, "Dog Walk", "Rememeber to walk the dog"));
        displayAllTasks(taskService);
        deleteTask(taskService, 3);
        displayAllTasks(taskService);
    }
}
