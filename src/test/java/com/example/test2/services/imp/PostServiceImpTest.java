package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.PostIn;
import com.example.test2.model.dtos.PostOut;
import com.example.test2.model.entities.PostEntity;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.repositories.CommentRepository;
import com.example.test2.repositories.PostRepository;
import com.example.test2.repositories.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(PostServiceImp.class)
public class PostServiceImpTest {

    @Spy
    @InjectMocks
    private PostServiceImp postServiceImp;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TagRepository tagRepository;

    @Test
    public void getAll_success() {
        List<PostEntity> postEntity = postEntities(5);
        Page<PostEntity> postEntities = new PageImpl<>(postEntity);
        when(postRepository.findAll(any(Pageable.class))).thenReturn(postEntities);
        List<PostOut> postOuts = postServiceImp.getAll(any(Pageable.class));
        assertNotNull(postOuts);
        assertEquals(postEntity.size(), postOuts.size());

    }

    @Test
    public void getAll_dontHaveAnyData_exception() {
        List<PostEntity> postEntities = new ArrayList<>();
        Page<PostEntity> entityPages = new PageImpl<>(postEntities);
        when(postRepository.findAll(any(Pageable.class))).thenReturn(entityPages);
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.getAll(any(Pageable.class)));
        assertEquals("you dont have any data", exception.getMessage());
        assertEquals(1004, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void create_success() {
        PostIn postIn = new PostIn();
        postIn.setTitle("doood");
        postIn.setPublishDate(LocalDateTime.parse("2024-08-09T12:20:30"));
        when(postRepository.save(any(PostEntity.class))).thenReturn(new PostEntity());
        postServiceImp.create(postIn);
    }

    @Test
    public void deleteById_success() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(new PostEntity()));
//        doNothing().when(postRepository).deleteById(1L);
        assertDoesNotThrow(() -> postServiceImp.deleteById(1L));
        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteById_idIsNotValid_exception() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.deleteById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void getById_success() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(new PostEntity()));
        when(postRepository.findById(1L)).thenReturn(Optional.of(new PostEntity()));
        PostOut entity = assertDoesNotThrow(() -> postServiceImp.getById(1L));
        assertNotNull(entity);

    }

    @Test
    public void getById_idIsNotValid_exception() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.getById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void updateById_success() {
        PostIn postIn = new PostIn();
        postIn.setTitle("doood");
        postIn.setPublishDate(LocalDateTime.parse("2024-08-09T12:20:30"));
        when(postRepository.findById(1L)).thenReturn(Optional.of(new PostEntity()));
        assertDoesNotThrow(() -> postServiceImp.updateById(1L, postIn));
        verify(postRepository, times(1)).updateById(1L, postIn);
    }

    @Test
    public void updateById_idIsNotValid_exception() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.getById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void addTagToPost_success() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        when(postRepository.findById(2L)).thenReturn(Optional.of(new PostEntity()));
        assertDoesNotThrow(() -> postServiceImp.addTagToPost(2L, 1L));
        verify(postRepository, times(1)).addTagToPost(1L, 2L);
    }

    @Test
    public void addTagToPost_idIsNotValid_exceptionForTagId() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.addTagToPost(2L, 1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void addTagToPost_exceptionForPostId() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        when(postRepository.findById(2L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.addTagToPost(2L, 1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void getAllTagAssignToPost_success() {
        when(postRepository.findById(3L)).thenReturn(Optional.of(new PostEntity()));
        postServiceImp.getAllTagAssignToPost(3L);
        verify(postRepository, times(1)).getAllTagAssignToPost(3L);
    }

    @Test
    public void getAllPostEntityWithRelationsByPostId_Success() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(new PostEntity()));
        when(postRepository.getAllPostEntityWithRelationsByPostId(1L)).thenReturn(this.postEntities(5));
        postServiceImp.getAllPostEntityWithRelationsByPostId(1L);
    }

    @Test
    public void getAllPostEntityWithRelationsByPostId_DontFindPostId_Exception() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.getAllPostEntityWithRelationsByPostId(1L));
        assertEquals("you dont have any data", exception.getMessage());
        assertEquals(1004, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void getAllTagAssignToPost_exception() {
        when(postRepository.findById(2L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,
                () -> postServiceImp.addTagToPost(2L, 1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    private PostEntity createPostEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("so loooong");
        postEntity.setPublishDate(LocalDateTime.parse("2024-08-09T12:20:30"));
        postEntity.setId(1L);
        return postEntity;
    }

    private List<PostEntity> postEntities(int count) {
        ArrayList<PostEntity> postEntities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PostEntity postEntity = createPostEntity();
            postEntity.setTitle("so farrrr" + i);
            postEntities.add(postEntity);
        }
        return postEntities;
    }
}