package com.example.test2.model.dtos;

import com.example.test2.model.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostOut {

    private String title;
    private Date publishDate;

    public PostOut(Post post) {
        this.title = post.getTitle();
        this.publishDate = post.getPublishDate();
    }
}
