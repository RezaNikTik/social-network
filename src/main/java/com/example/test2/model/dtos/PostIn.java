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
public class PostIn {


    private String title;
    private Date publishDate;

    public Post convertToPost (Post post) {
        if (post == null){
            new Post();
        }
        setTitle(this.title);
        setPublishDate(this.publishDate);
        return post;
    }
}
