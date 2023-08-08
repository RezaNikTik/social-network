package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImpTest {

    @Spy
    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;


    AutoCloseable openMocks;


    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

//    @BeforeEach
//    void setCommentEntity(){
//        CommentIn comment = new CommentIn();
//        comment.setPostId(1L);
//        comment.setMessage("baaaaaaaaaaaz");
//    }


    @Test
    public void getAll_success() {
        List<CommentEntity> commentEntities = createCommentEntities(5);
        Mockito.doReturn(commentEntities).when(commentRepository).findAll();
        List<CommentOut> comments = commentServiceImp.getAll();
        assertNotNull(comments);
        assertEquals(commentEntities.size(), comments.size());
//        assertEquals("baaaaaaaaaaaz",comment);
    }

    @Test
    @ExceptionHandler(CustomException.class)
    public void getAll_exception() throws CustomException {
        Mockito.doReturn(new ArrayList<>()).when(commentRepository).findAll();
        List<CommentOut> comments = commentServiceImp.getAll();
        assertThrows(CustomException.class,
                () -> comments.isEmpty());
        assertNull(comments);
//        assertEquals(commentEntities.size(), comments.size());
//        assertEquals("baaaaaaaaaaaz",comment);
    }

    @Test
    public void create() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void updateById() {
    }


    private CommentEntity createCommentEntity() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setMessage("bia");
        return commentEntity;
    }

    private List<CommentEntity> createCommentEntities(int count) {
        List<CommentEntity> commentEntities = new ArrayList();
        for (int i = 0; i < count; i++) {
            CommentEntity commentEntity = createCommentEntity();
            commentEntity.setId((long) i);
            commentEntity.setMessage("message" + i);
            commentEntities.add(commentEntity);
        }

        return commentEntities;
    }

    private PostEntity createPostEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("hii");
        postEntity.setPublishDate(LocalDateTime.parse("2023-04-22T20:30:00"));
        postEntity.setId(1L);
        return postEntity;
    }
}