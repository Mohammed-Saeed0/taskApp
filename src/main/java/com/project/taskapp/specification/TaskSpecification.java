package com.project.taskapp.specification;

import com.project.taskapp.entity.Task;
import com.project.taskapp.dto.TaskSearch;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {
    public static Specification<Task> getTasks(String title, String description, String status)
    {
        return ((Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Task title
            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }

            // Task description
            if (description != null && !description.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }

            // Task status
            if (status != null && !status.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
