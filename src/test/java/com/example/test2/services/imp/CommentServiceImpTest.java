package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.CommentIn;
import com.example.test2.model.dtos.CommentOut;
import com.example.test2.model.entities.CommentEntity;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(CommentServiceImp.class)
public class CommentServiceImpTest {

    @Spy
    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;


    @Test
    public void getAll_success() {
        List<CommentEntity> commentEntities = createCommentEntities(5);
        Page<CommentEntity> entityPage = new PageImpl<>(commentEntities);
        when(commentRepository.findAll(any(Pageable.class))).thenReturn(entityPage);
        List<CommentOut> comments = commentServiceImp.getAll(0, 3);
        assertNotNull(comments);
        assertEquals(commentEntities.size(), comments.size());
    }

    @Test
    public void getAll_dontHaveAnyData_exception() throws CustomException {
        List<CommentEntity> list = new ArrayList<>();
        Page<CommentEntity> entityPage = new PageImpl<>(list);
        when(commentRepository.findAll(any(Pageable.class))).thenReturn(entityPage);
        CustomException exception = assertThrows(CustomException.class,
                () -> commentServiceImp.getAll(0, 5));

        assertEquals("you dont have any data", exception.getMessage());
        assertEquals(1004, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void create_success() {
        CommentIn commentIn = new CommentIn();
        commentIn.setPostId(1L);
        commentIn.setMessage("broooooo");
        when(postRepository.findById(1L)).thenReturn(Optional.of(new PostEntity()));
        CommentEntity savedCommentEntity = new CommentEntity();
        savedCommentEntity.setMessage(commentIn.getMessage());
        savedCommentEntity.setPostEntity(this.createPostEntity());
        when(commentRepository.save(any(CommentEntity.class))).thenReturn(savedCommentEntity);
        CommentOut createdComment = commentServiceImp.create(commentIn);
        assertEquals(savedCommentEntity.getMessage(), createdComment.getMessage());
    }

    @Test
    public void create_exception() {
        CommentIn commentIn = new CommentIn();
        commentIn.setPostId(2L);
        commentIn.setMessage("nayaaa");
        when(postRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> commentServiceImp.create(commentIn));
    }

    @Test
    public void deleteById_success() {
        CommentEntity commentEntity = this.createCommentEntity();
        when(commentRepository.findById(commentEntity.getId())).thenReturn(Optional.of(new CommentEntity()));
        doNothing().when(commentRepository).deleteById(commentEntity.getId());
        assertDoesNotThrow(() -> commentServiceImp.deleteById(commentEntity.getId()));
        verify(commentRepository, times(1)).deleteById(commentEntity.getId());
    }

    @Test
    public void deleteById_idIsNotValid_exception() {
        CommentEntity commentEntity = this.createCommentEntity();
        when(commentRepository.findById(commentEntity.getId())).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> commentServiceImp.deleteById(commentEntity.getId()));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void getById_success() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(new CommentEntity()));
        CommentOut comment = commentServiceImp.getById(1L);
        assertNotNull(comment);
    }

    @Test
    public void getById_idIsNotValid_exception() {
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> commentServiceImp.getById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void updateById_success() {
        CommentIn commentIn = new CommentIn();
        commentIn.setMessage("jegarrr");
        when(commentRepository.findById(1L)).thenReturn(Optional.of(new CommentEntity()));
        assertDoesNotThrow(() -> commentServiceImp.updateById(1L, commentIn));
        verify(commentRepository).updateById(1L, commentIn);
    }

    @Test
    public void updateById_idIsNotValid_exception() {
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> commentServiceImp.updateById(1L, this.createCommentIn()));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }


    private CommentEntity createCommentEntity() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setMessage("bia");
        commentEntity.setPostEntity(this.createPostEntity());
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

    private CommentIn createCommentIn() {
        CommentIn commentIn = new CommentIn();
        commentIn.setMessage("jegarrr");
        return commentIn;
    }
}