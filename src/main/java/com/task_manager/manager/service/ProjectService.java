package com.task_manager.manager.service;

import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.entity.Project;
import com.task_manager.manager.entity.User;

import java.util.List;

public interface ProjectService {
    void createProject(Project project);
    List<Project> findProjectsByUser();
    void addUserToProject(Long userId, Long id) throws ExpectedException;



}
