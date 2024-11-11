package com.project.taskapp.controller;
import com.project.taskapp.dto.TaskDTO;
import com.project.taskapp.dto.TaskSearch;
import com.project.taskapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public TaskDTO getTaskById(@PathVariable Long id) {
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
    public String updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO) {
        taskService.updateTask(id, taskDTO);
        return "task is updated successfully";
    }

    // delete a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "task is deleted successfully";
    }

    // Using Specification to find task by taskTitle
    @GetMapping("/specification")
    public ResponseEntity<?> getFilteredTasks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "status", required = false) String status)
    {
        return ResponseEntity.ok(taskService.getFilteredTasks(title, description, status));
    }
}
