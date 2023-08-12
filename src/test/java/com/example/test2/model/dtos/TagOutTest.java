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

    @Test
    public void constructor_WithNullTagEntity() {
        TagOut tagOut = new TagOut(null);

    }

    @Test
    public void constructor_WithNonNullTagEntity() {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("bib");
        tagEntity.setId(1L);

        TagOut tagOut = new TagOut(tagEntity);

        assertEquals(tagEntity.getId(), tagOut.getId());
        assertEquals(tagEntity.getName(), tagOut.getName());
    }

}