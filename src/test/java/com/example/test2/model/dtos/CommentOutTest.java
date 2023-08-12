package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CommentOutTest {

    @Test
    public void constructor_WithUninitializedCommentEntity_Success() {
        new CommentOut(null);
    }

    @Test
    public void constructor_WithoutRelations_Success() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMessage("test");

        CommentOut commentOuts = new CommentOut(commentEntity);

        assertEquals(commentEntity.getMessage(), commentOuts.getMessage());
    }

    @Test
    public void constructor_WithRelations_Success() {
        CommentEntity commentEntity = createWithRelations();
        CommentOut commentOuts = new CommentOut(commentEntity);

        assertEquals(commentEntity.getMessage(), commentOuts.getMessage());
        assertEquals(commentEntity.getPostEntity().getId(), commentOuts.getPostId());
    }

    private CommentEntity createWithoutRelations() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMessage("test");
        return commentEntity;
    }

    private CommentEntity createWithRelations() {
        CommentEntity commentEntity = createWithoutRelations();
        commentEntity.setPostEntity(createPostEntity());
        return commentEntity;
    }

    private PostEntity createPostEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);
        return postEntity;
    }
}