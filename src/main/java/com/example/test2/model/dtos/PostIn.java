package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostIn {


    private String title;

    private String nameTag;

    public PostEntity convertToPost (PostEntity postEntity) {
        if (postEntity == null){
            new PostEntity();
        }
        postEntity.setTitle(this.title);
        return postEntity;
    }

/*    public PostEntity addTagToPost(PostEntity postEntity,TagEntity tagEntity){
        if (postEntity == null){
            new PostEntity();
        }
        Set<TagEntity> tagEntities =new HashSet<>();
        postEntity.setTagEntity(tagEntities.add(tagEntity.setName();));

    }
       */


}
