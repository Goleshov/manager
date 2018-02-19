package com.task_manager.manager.service.impl;

import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.entity.Project;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Role;
import com.task_manager.manager.repository.ProjectRepository;
import com.task_manager.manager.repository.UserRepository;
import com.task_manager.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
     @Autowired
    UserRepository userRepository;
    @Override
    public void createProject(Project project) {
        String email =SecurityContextHolder.getContext().getAuthentication().getName();
        User manager = userRepository.findByEmail(email);
        project.getUsers().add(manager);
        projectRepository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findProjectsByUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return projectRepository.findProjectsByUsersEmail(email);
    }

    @Override
    public void addUserToProject(Long userId, Long id) throws ExpectedException {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new ExpectedException("Can not find user");
        }
        Project project = projectRepository.findOne(id);
        if (project == null) {
            throw new ExpectedException("Can not find project");
        } else if (user == null || !user.getIsActivated() ||
                !user.getAuthority().getRole().equals(Role.ROLE_USER.toString())
                || project.getUsers().contains(user)) {
            throw new ExpectedException("Invalid user");
        }
        project.getUsers().add(user);
    }
}

