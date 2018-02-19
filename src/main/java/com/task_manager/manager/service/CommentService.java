package com.task_manager.manager.service;

import com.task_manager.manager.entity.Comment;
import com.task_manager.manager.entity.DTO.CommentDTO;
import com.task_manager.manager.exception.ExpectedException;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByTaskId(Long id);
    void createComment(Long taskId, String text) throws ExpectedException;
    void deleteComment(Long id) throws ExpectedException;
    void updateComment(Long id, String text)throws ExpectedException;
}
