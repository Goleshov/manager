package com.task_manager.manager.repository;

import com.task_manager.manager.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findTasksByProjectId(Long id);
    List<Task> findTasksByUsersEmail(String email);



}
