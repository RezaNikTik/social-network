package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostIn {


    private String title;

    public PostEntity convertToPost (PostEntity postEntity) {
        if (postEntity == null){
            new PostEntity();
        }
        postEntity.setTitle(this.title);
        return postEntity;
    }



}
