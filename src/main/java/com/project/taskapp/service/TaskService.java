package com.project.taskapp.service;

import com.project.taskapp.dto.TaskDTO;
import com.project.taskapp.mapper.TaskMapper;
import com.project.taskapp.repository.TaskRepository;
import com.project.taskapp.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;


    // Retrieve all tasks
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a task by id
    public TaskDTO getTaskById(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.toTaskDTO(task);
    }

    // Create a new task
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toTask(taskDTO);
        task = taskRepository.save(task);
        return taskMapper.toTaskDTO(task);
    }

    // Update a task by id
    public TaskDTO updateTask(Integer id, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setDueDate(taskDTO.getDueDate());
        taskRepository.save(existingTask);
        return taskMapper.toTaskDTO(existingTask);
    }

    // Delete a task by id
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
