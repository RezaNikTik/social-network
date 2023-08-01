package com.example.test2.model.dtos;

import com.example.test2.model.entities.Comment;
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

    public Comment convertToComment(Comment comment) {
        if (comment == null){
            new Comment();
        }
        comment.setMessage(this.getMessage());
        return comment;
    }
}
