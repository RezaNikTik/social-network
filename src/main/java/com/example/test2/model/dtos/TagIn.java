package com.example.test2.model.dtos;

import com.example.test2.model.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagIn {

    private String name;



    public TagEntity convertToTag(TagEntity tagEntity) {
        if (tagEntity == null){
            new TagEntity();
        }
        tagEntity.setName(this.getName());
        return tagEntity;
    }
}
