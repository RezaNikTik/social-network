package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentInTest {

    @Test
    public void convertToComment_ValidInput_Success() {


        CommentIn commentIn = new CommentIn();
        commentIn.setMessage("Test message");

        CommentEntity mockCommentEntity = mock(CommentEntity.class);

        CommentEntity result = commentIn.convertToComment(mockCommentEntity);

        assertEquals(mockCommentEntity.getMessage(), result.getMessage());
        verify(mockCommentEntity, times(1)).setMessage(commentIn.getMessage());
    }

    @Test
    public void convertToComment_NullInput_Success() {
        CommentIn commentIn = new CommentIn();
        commentIn.setMessage("Test message");

        CommentEntity result = commentIn.convertToComment(null);

        assertEquals(commentIn.getMessage(), result.getMessage());
    }

}