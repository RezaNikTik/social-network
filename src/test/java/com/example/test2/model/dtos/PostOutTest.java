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
    public void testConvertToPostWithNonNullEntity() {
        PostEntity mockPostEntity = Mockito.mock(PostEntity.class);
        when(mockPostEntity.getTitle()).thenReturn("Test Title");
        LocalDateTime publishDate = LocalDateTime.of(2023, 5, 17, 10, 30);
        when(mockPostEntity.getPublishDate()).thenReturn(publishDate);

        PostOut postOut = new PostOut(mockPostEntity);

        assertEquals("Test Title", postOut.getTitle());
        assertEquals(publishDate, postOut.getPublishDate());
    }

    @Test
    public void testConvertToPostWithNullEntity() {
        PostEntity mockPostEntity = null;

        PostOut postOut = new PostOut(mockPostEntity);

        assertEquals(null, postOut.getTitle());
        assertEquals(null, postOut.getPublishDate());
    }

}