package com.task_manager.manager.service.impl;

import com.task_manager.manager.entity.DTO.TaskDTO;
import com.task_manager.manager.entity.Project;
import com.task_manager.manager.entity.Task;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Role;
import com.task_manager.manager.entity.enums.Status;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.repository.ProjectRepository;
import com.task_manager.manager.repository.TaskRepository;
import com.task_manager.manager.repository.UserRepository;
import com.task_manager.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Task> findTasksByProjectId(Long id) {
        return taskRepository.findTasksByProjectId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findTaskById(Long id) throws ExpectedException {
        Task task = taskRepository.findOne(id);
        if (task != null) {
            return TaskDTO.convertToTaskDTO(taskRepository.findOne(id));
        } else {
            throw new ExpectedException("Task not found");
        }
    }

    @Override
    public void createTask(Long projectId, Task task) throws ExpectedException {
        Project project = projectRepository.findOne(projectId);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (project == null) {
            throw new ExpectedException("Cannot find project");
        } else if (!project.getUsers().contains(user)) {
            throw new ExpectedException("Cannot add a task ti project you're not working at");
        } else {
            task.setProject(project);
            task.setStatus(Status.WAITING);
            taskRepository.save(task);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findTasksByUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return TaskDTO.convertToTaskDTO(taskRepository.findTasksByUsersEmail(email));
    }

    @Override
    public void updateTaskStatus(Status status, Long id) throws ExpectedException {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new ExpectedException("Task not found");
        }
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public void addUserToTask(Long userId, Long id) throws ExpectedException {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new ExpectedException("Can not find user");
        }
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new ExpectedException("Can not find task");
        } else if (user == null || !user.getIsActivated() ||
                !user.getAuthority().getRole().equals(Role.ROLE_USER.toString())
                || task.getUsers().contains(user)) {
            throw new ExpectedException("Invalid user");
        }
        task.getUsers().add(user);
    }
}
