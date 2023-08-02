package com.example.test2.model.dtos;

import com.example.test2.model.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagOut {

    private String name;


    public TagOut (TagEntity tagEntity) {
        this.name = tagEntity.getName();
    }
}
