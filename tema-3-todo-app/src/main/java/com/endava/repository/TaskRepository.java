package com.endava.repository;

import com.endava.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    public TaskRepository() {
        System.out.println("in repository constructor");
    }
    private List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "Groceries", "buy milk, nutella, and bread"),
            new Task(2, "Cleaning", "clean the house"),
            new Task(3, "Homework", "do homework")
    ));

    public List<Task> listAll() {
        return tasks;
    }

    public Optional<Task> findById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public void addTask(Task task) {
        if (tasks.stream().anyMatch(t -> t.getId() == task.getId())) {
            throw new IllegalArgumentException("Task with the same ID already exists");
        }
        tasks.add(task);
    }

    public void removeTask(Task task) {
        if (!tasks.remove(task)) {
            throw new IllegalArgumentException("Task does not exist");
        }
    }
}
