package com.example.test2.model.dtos;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentOutTest {

    @Spy
    @InjectMocks
    private CommentOut commentOut;

    @Mock
    private CommentEntity mockCommentEntity;


    @Test
    public void constructor_WithInitializedCommentEntity_Success() {
        when(mockCommentEntity.getMessage()).thenReturn("Test comment");

        PostEntity mockPostEntity = mock(PostEntity.class);
        when(mockPostEntity.getId()).thenReturn(1L);
        when(mockCommentEntity.getPostEntity()).thenReturn(mockPostEntity);

        CommentOut commentOut = new CommentOut(mockCommentEntity);

        assertEquals(mockCommentEntity.getMessage(), commentOut.getMessage());
        assertEquals(mockCommentEntity.getPostEntity().getId(), commentOut.getPostId());
    }

    @Test
    public void constructor_WithUninitializedCommentEntity_Success() {
        CommentOut commentOut = new CommentOut(null);

        assertEquals(null, commentOut.getMessage());
        assertEquals(null, commentOut.getPostId());
    }
}