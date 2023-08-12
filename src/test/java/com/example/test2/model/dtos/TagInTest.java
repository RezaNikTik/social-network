package com.example.test2.model.dtos;

import com.example.test2.model.entities.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TagInTest {


    @Mock
    private TagEntity mockTagEntity;

    @Test
    public void testConvertToTag_NewTagEntity() {
        TagIn tagIn = new TagIn();
        tagIn.setName("TagName");

        TagEntity result = tagIn.convertToTag(null);

        assertEquals("TagName", result.getName());
    }

    @Test
    public void testConvertToTag_ExistingTagEntity() {
        TagIn tagIn = new TagIn();
        tagIn.setName("NewTagName");

        when(mockTagEntity.getName()).thenReturn("OldTagName");

        TagEntity result = tagIn.convertToTag(mockTagEntity);

        assertEquals("NewTagName", result.getName());
    }
}