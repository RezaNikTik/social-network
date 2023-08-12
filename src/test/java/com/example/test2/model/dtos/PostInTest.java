package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PostInTest {


    @Test
    public void convertToPost_entityIsNull_Success() {
        PostIn postIn = new PostIn();
        postIn.setTitle("Test title");
        postIn.setPublishDate(LocalDateTime.now());

        postIn.convertToEntity(null);
    }

    @Test
    public void convertToEntity_entityIsNotNull_Success() {
        PostIn postIn = new PostIn();
        postIn.setTitle("Test title");
        postIn.setPublishDate(LocalDateTime.now());

        PostEntity post = new PostEntity();
        postIn.convertToEntity(post);

        assertEquals(postIn.getTitle(), post.getTitle());
        assertEquals(postIn.getPublishDate(), post.getPublishDate());
    }


}