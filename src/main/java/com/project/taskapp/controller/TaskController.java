package com.project.taskapp.controller;
import com.project.taskapp.dto.TaskDTO;
import com.project.taskapp.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@Validated
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
    public String createTask(@RequestBody @Valid TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return "task is created successfully";
    }

    // update a task
    @PutMapping("/{id}")
    public String updateTask(@PathVariable Integer id, @RequestBody @Valid TaskDTO taskDTO) {
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

