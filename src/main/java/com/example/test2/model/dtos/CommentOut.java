package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentOut implements Serializable {

    private String message;

    private Long postId;

    public CommentOut(CommentEntity commentEntity) {
        if (commentEntity != null) {
            this.message = commentEntity.getMessage();
            this.postId= commentEntity.getPost_Id();

            if (Hibernate.isInitialized(commentEntity.getPostEntity()) && commentEntity.getPostEntity() != null) {
                this.postId = commentEntity.getPostEntity().getId();
            }
        }
    }
}
