package com.project.taskapp.service;

import com.project.taskapp.dto.TaskDTO;
import com.project.taskapp.dto.TaskSearch;
import com.project.taskapp.exception.TaskNotFoundException;
import com.project.taskapp.mapper.TaskMapper;
import com.project.taskapp.repository.TaskRepository;
import com.project.taskapp.entity.Task;
import com.project.taskapp.repository.TaskSpecification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    // Retrieve all tasks
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a task by id
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        return taskMapper.toTaskDTO(task);
    }

    // Create a new task
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toTask(taskDTO);
        task = taskRepository.save(task);
        return taskMapper.toTaskDTO(task);
    }

    // Update a task by id
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setDueDate(taskDTO.getDueDate());
        taskRepository.save(existingTask);
        return taskMapper.toTaskDTO(existingTask);
    }

    // Delete a task by id
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


    // Using Specification to find task by taskTitle
    public List<Task> findByTaskTitle(TaskSearch taskSearch)
    {
        TaskSpecification taskSpecification = new TaskSpecification(taskSearch);
        return taskRepository.findAll(taskSpecification);
    }


}
