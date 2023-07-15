package com.endava;

import com.endava.model.Task;
import com.endava.repository.TaskRepository;
import com.endava.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.endava")
public class AppConfig {
    @Bean(name = "repository")
    public TaskRepository getTaskRepository() {
        return new TaskRepository();
    }

    @Bean
    public TaskService taskService() {
        return new TaskService(getTaskRepository());
    }
}
