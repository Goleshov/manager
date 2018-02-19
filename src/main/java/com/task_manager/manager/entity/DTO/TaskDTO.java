package com.task_manager.manager.entity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task_manager.manager.entity.Task;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.entity.enums.Status;
import com.task_manager.manager.service.CommentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDTO {
    @Autowired
    @JsonIgnore
    CommentService commentService;
    private Long id;
    private String description;
    private String name;
    private Status status;
    private List<Long> users = new ArrayList<>();
    private List<CommentDTO> comments;

    public static TaskDTO convertToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setName(task.getName());
        taskDTO.setStatus(task.getStatus());

        for (User user : task.getUsers()) {
            taskDTO.getUsers().add(user.getId());
        }
        taskDTO.setComments(CommentDTO.convertToCommentDTO(task.getComments()));
        return taskDTO;
    }

    public static List<TaskDTO> convertToTaskDTO(List<Task> tasks) {
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (Task task : tasks) {
            taskDTOList.add(convertToTaskDTO(task));
        }
        return taskDTOList;
    }
}
