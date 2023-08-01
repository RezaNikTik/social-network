package com.example.test2.model.dtos;

import com.example.test2.model.entities.Tag;
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


    public TagOut (Tag tag) {
        this.name = tag.getName();
    }
}
