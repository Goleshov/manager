package com.task_manager.manager.repository;

import com.task_manager.manager.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentRepository extends CrudRepository<Comment, Long> {
   List<Comment> findCommentsByTaskId(Long id);
}
