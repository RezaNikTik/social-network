package com.example.test2.model.dtos;

import com.example.test2.model.entities.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TagInTest {


    @Test
    public void convertToEntity_WithNullTagEntity() {
        TagIn tagIn = new TagIn();
        tagIn.setName("TagName");

        TagEntity results = tagIn.convertToEntity(null);

        assertEquals("TagName", results.getName());
    }

    @Test
    public void convertToEntity_WithTagEntity() {
        TagIn tagIn = new TagIn();
        tagIn.setName("NewTagName");

        TagEntity tagEntity = new TagEntity();
        TagEntity results = tagIn.convertToEntity(tagEntity);

        assertEquals(tagIn.getName(), results.getName());
    }
}