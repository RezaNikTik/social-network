package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentOut {

    private String message;

    private Long postId;

    public CommentOut(CommentEntity commentEntity) {
        if (commentEntity != null) {
            this.message = commentEntity.getMessage();

            if (Hibernate.isInitialized(commentEntity.getPostEntity()) && commentEntity.getPostEntity() != null) {
                this.postId = commentEntity.getPostEntity().getId();
            }
        }
    }
}
