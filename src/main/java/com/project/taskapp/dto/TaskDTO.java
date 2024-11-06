package com.project.taskapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
public class TaskDTO {
    private Integer id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    private String description;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @NotNull(message = "Due date cannot be null")
    private Date dueDate;
}
