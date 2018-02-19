package com.task_manager.manager.entity.DTO;

import com.task_manager.manager.entity.Comment;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentDTO {

    private Long id;
    private String text;
    private String authorName;
    private String authorSurname;
    private Boolean canModify;

    private static CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        commentDTO.setAuthorName(comment.getUser().getName());
        commentDTO.setAuthorSurname(comment.getUser().getSurname());
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(comment.getUser().getEmail())) {
            commentDTO.setCanModify(true);
        } else {
            commentDTO.setCanModify(false);
        }
        return commentDTO;
    }

    public static List<CommentDTO> convertToCommentDTO(List<Comment> comments) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOList.add(convertToCommentDTO(comment));
        }
        return commentDTOList;
    }
}
