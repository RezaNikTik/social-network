package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostOutTest {


    @Test
    public void convertToEntity_WithNullEntity() {
        new PostOut(null);
    }

    @Test
    public void convertToEntity_WithNonNullEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("Test Title");
        postEntity.setPublishDate(LocalDateTime.of(2023, 6, 12, 17, 20));
        PostOut postOuts = new PostOut(postEntity);
        assertEquals(postEntity.getTitle(), postOuts.getTitle());
        assertEquals(postEntity.getPublishDate(), postOuts.getPublishDate());
    }

}