package com.example.test2.model.dtos;

import com.example.test2.model.entities.Tag;
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



    public Tag convertToTag(Tag tag) {
        if (tag == null){
            new Tag();
        }
        tag.setName(this.getName());
        return tag;
    }
}
