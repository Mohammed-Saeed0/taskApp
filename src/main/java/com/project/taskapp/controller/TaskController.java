package com.project.taskapp.controller;
import com.project.taskapp.dto.TaskDTO;
import com.project.taskapp.entity.Task;
import com.project.taskapp.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    // Retrieve all tasks
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // retrieve a task by id
    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    // create a new task
    @PostMapping
    public String createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return "task is created successfully";
    }

    // update a task
    @PutMapping("/{id}")
    public String updateTask(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        taskService.updateTask(id, taskDTO);
        return "task is updated successfully";
    }

    // delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return "task is deleted successfully";
    }
}

