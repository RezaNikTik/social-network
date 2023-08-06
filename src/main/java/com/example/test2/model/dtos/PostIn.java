package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostIn {


    private String title;
    private LocalDateTime publishDate;
    public PostEntity convertToPost (PostEntity postEntity) {
        if (postEntity == null){
            postEntity= new PostEntity();
        }
        postEntity.setTitle(this.title);
        postEntity.setPublishDate(this.publishDate);
        return postEntity;
    }



}
