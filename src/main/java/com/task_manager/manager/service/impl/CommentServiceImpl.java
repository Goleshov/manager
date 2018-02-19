package com.task_manager.manager.service.impl;

import com.task_manager.manager.entity.Comment;
import com.task_manager.manager.entity.DTO.CommentDTO;
import com.task_manager.manager.entity.Task;
import com.task_manager.manager.entity.User;
import com.task_manager.manager.exception.ExpectedException;
import com.task_manager.manager.repository.CommentRepository;
import com.task_manager.manager.repository.TaskRepository;
import com.task_manager.manager.repository.UserRepository;
import com.task_manager.manager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByTaskId(Long id) {
        return CommentDTO.convertToCommentDTO(commentRepository.findCommentsByTaskId(id));
    }

    @Override
    public void createComment(Long taskId, String text) throws ExpectedException {
        Task task = taskRepository.findOne(taskId);
        if (task == null) {
            throw new ExpectedException("Cannot find task");
        }
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setText(text);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) throws ExpectedException {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            throw new ExpectedException("Cannot delete comment.Comment doesn't exist");
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null || !comment.getUser().equals(user)) {
            throw new ExpectedException("You have no rights to delete this comment");
        }
        commentRepository.delete(id);
    }

    @Override
    public void updateComment(Long id, String text) throws ExpectedException {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            throw new ExpectedException("Cannot update comment. Comment doesn't exist");
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if (user == null || !comment.getUser().equals(user)) {
            throw new ExpectedException("You have no rights to update this comment");
        }
        comment.setText(text);
        commentRepository.save(comment);
    }
}
