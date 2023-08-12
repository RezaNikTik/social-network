package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentIn {

    private String message;
    private Long postId;

    public CommentEntity convertToEntity(CommentEntity commentEntity) {
        if (commentEntity == null) {
            commentEntity = new CommentEntity();
        }
        commentEntity.setMessage(this.getMessage());
        return commentEntity;
    }

}
