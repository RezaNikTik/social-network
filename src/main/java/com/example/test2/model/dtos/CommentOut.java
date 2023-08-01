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
public class CommentOut {

    private String message;

    public CommentOut(Comment comment) {
        this.message = comment.getMessage();
    }
}
