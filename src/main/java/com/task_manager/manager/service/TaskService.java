package com.task_manager.manager.service;

import com.task_manager.manager.entity.DTO.TaskDTO;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.entity.Task;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Status;

import java.util.List;

public interface TaskService {
    TaskDTO findTaskById(Long id) throws ExpectedException;
    List<Task> findTasksByProjectId(Long id);

    void createTask(Long projectID, Task task) throws ExpectedException;

    List<TaskDTO> findTasksByUser();

    void updateTaskStatus(Status status, Long id) throws ExpectedException;

    void addUserToTask(Long userId, Long id) throws ExpectedException;


}
