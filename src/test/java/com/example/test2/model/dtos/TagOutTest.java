package com.example.test2.model.dtos;

import com.example.test2.model.entities.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagOutTest {


    @Mock
    private TagEntity mockTagEntity;

    @Test
    public void constructor_WithNonNullTagEntity() {
        when(mockTagEntity.getId()).thenReturn(1L);
        when(mockTagEntity.getName()).thenReturn("TagName");

        TagOut tagOut = new TagOut(mockTagEntity);

        assertEquals(1L, tagOut.getId());
        assertEquals("TagName", tagOut.getName());
    }

    @Test
    public void constructor_WithNullTagEntity() {
        TagOut tagOut = new TagOut(null);

        assertEquals(null, tagOut.getId());
        assertEquals(null, tagOut.getName());
    }

}