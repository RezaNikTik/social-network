package com.example.test2.model.dtos;

import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

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

    @Test
    public void convertToEntity_WithNonNullEntityAndInitialComment() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("Test Title");
        postEntity.setPublishDate(LocalDateTime.of(2023, 6, 12, 17, 20));
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setMessage("bogh");
        commentEntity.setPostEntity(postEntity);
        postEntity.setCommentEntityPost(Collections.singleton(commentEntity));
        PostOut postOuts = new PostOut(postEntity);
        assertEquals(postEntity.getTitle(), postOuts.getTitle());
        assertEquals(postEntity.getCommentEntityPost().size(), postOuts.getComments().size());
    }

    @Test
    public void convertToEntity_WithNonNullEntityAndInitialTag() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("Test Title");
        postEntity.setPublishDate(LocalDateTime.of(2023, 6, 12, 17, 20));
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("hala harchi");
        postEntity.setTagEntity(Collections.singleton(tagEntity));
        PostOut postOuts = new PostOut(postEntity);
        assertEquals(postEntity.getTitle(), postOuts.getTitle());
        assertEquals(postEntity.getTagEntity().size(), postOuts.getTags().size());
    }

}