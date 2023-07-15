package com.endava.service;

import com.endava.model.Task;
import com.endava.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
  private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.listAll();
    }

    public Optional<Task> findTask(int id) {
        return taskRepository.findById(id);
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public void removeTask(int id) {
        Optional<Task> taskToRemove = findTask(id);
        taskToRemove.ifPresent(task -> taskRepository.removeTask(task));
    }
}
