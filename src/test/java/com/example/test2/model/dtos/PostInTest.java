package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostInTest {


    @Mock
    private PostEntity postEntity;


    @Test
    public void convertToPost_ValidInput_Success() {
        PostIn postIn = new PostIn();
        postIn.setTitle("Test title");
        postIn.setPublishDate(LocalDateTime.now());

        PostEntity post = new PostEntity();

        PostEntity result = postIn.convertToPost(post);

        assertEquals(postIn.getTitle(), result.getTitle());
        assertEquals(postIn.getPublishDate(), result.getPublishDate());
    }

    @Test
    public void convertToPost_NullInput_Success() {
        PostIn postIn = new PostIn();
        postIn.setTitle("Test title");
        postIn.setPublishDate(LocalDateTime.now());

        PostEntity result = postIn.convertToPost(null);

        assertEquals(postIn.getTitle(), result.getTitle());
        assertEquals(postIn.getPublishDate(), result.getPublishDate());
    }

}