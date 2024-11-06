package com.project.taskapp.mapper;

import com.project.taskapp.dto.TaskDTO;
import com.project.taskapp.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toTaskDTO(Task task); // convert from task entity to task dto
    Task toTask(TaskDTO taskDTO);  // convert from task dto to task entity
}

