package com.project.taskapp.repository;

import com.project.taskapp.entity.Task;
import com.project.taskapp.dto.TaskSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification implements Specification<Task> {

    private TaskSearch taskSearch;
    private String taskTitle;

    public TaskSpecification(TaskSearch taskSearch) {
        this.taskSearch = taskSearch;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        // title of task
        if (taskSearch.getTitle() != null && !taskSearch.getTitle().isEmpty())
        {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" +taskSearch.getTitle() + "%"));


        }
        // title of task
        if (taskSearch.getDescription() != null && !taskSearch.getDescription().isEmpty())
        {
            predicates.add(criteriaBuilder.like(root.get("description"),taskSearch.getDescription()));
        }
        // title of status
        if (taskSearch.getStatus() != null && !taskSearch.getStatus().isEmpty())
        {
            predicates.add(criteriaBuilder.like(root.get("status"),taskSearch.getStatus()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        return criteriaBuilder.like(root.get("title"), taskTitle);
    }

}
