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

        CommentEntity mockCommentEntity = new CommentEntity();
        commentIn.convertToEntity(mockCommentEntity);
        assertEquals(commentIn.getMessage(), mockCommentEntity.getMessage());
    }

    @Test
    public void convertToComment_NullInput_Success() {
        CommentIn commentIn = new CommentIn();
        commentIn.setMessage("Test message");
        CommentEntity result = commentIn.convertToEntity(null);
        assertEquals(commentIn.getMessage(), result.getMessage());
    }

}