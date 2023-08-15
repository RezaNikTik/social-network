package com.example.test2.model.dtos;

import com.example.test2.model.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagOut implements Serializable {
    private Long id;
    private String name;

    public TagOut(TagEntity tagEntity) {
        if (tagEntity != null) {
            this.id = tagEntity.getId();
            this.name = tagEntity.getName();
        }
    }
}
