package com.task_manager.manager.repository;

import com.task_manager.manager.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findProjectsByUsersEmail(String email);


}
