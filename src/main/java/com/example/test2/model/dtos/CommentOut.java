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
public class CommentOut {

    private String message;

    private Long postId;

    public CommentOut(CommentEntity commentEntity) {
        this.message = commentEntity.getMessage();
        this.postId = commentEntity.getId();
    }
}
