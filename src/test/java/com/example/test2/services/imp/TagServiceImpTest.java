package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.TagIn;
import com.example.test2.model.dtos.TagOut;
import com.example.test2.model.entities.TagEntity;
import com.example.test2.repositories.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(TagServiceImp.class)
public class TagServiceImpTest {


    @Spy
    @InjectMocks
    private TagServiceImp tagServiceImp;

    @Mock
    private TagRepository tagRepository;

    @Test
    public void getAll_success() {
        List<TagEntity> list = tagEntities(5);
        when(tagRepository.findAll()).thenReturn(list);
        List<TagOut> tag = tagServiceImp.getAll(5);
        assertNotNull(tag);
        assertEquals(list.size(), tag.size());
    }

    @Test
    public void getAll_dontHaveAnyData_exception() {
        List<TagEntity> tagEntities = new ArrayList<>();
        when(tagRepository.findAll()).thenReturn(tagEntities);
        CustomException exception = assertThrows(CustomException.class, () -> tagServiceImp.getAll(5));
        assertEquals("you dont have any data", exception.getMessage());
        assertEquals(1004, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void create_success() {
        TagIn tagIn = new TagIn();
        tagIn.setName("electronic");
        when(tagRepository.save(any(TagEntity.class))).thenReturn(new TagEntity());
        TagOut tagOut = tagServiceImp.create(tagIn);

    }


    @Test
    public void deleteById_success() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        assertDoesNotThrow(() -> tagServiceImp.deleteById(1L));
        verify(tagRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteById_idIsNotValid_exception() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> tagServiceImp.deleteById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void getById_success() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        TagOut tagOut = tagServiceImp.getById(1L);
        assertNotNull(tagOut);
    }

    @Test
    public void getById_idIsNotValid_exception() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> tagServiceImp.getById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void updateById_success() {
        TagIn tagIn = new TagIn();
        tagIn.setName("electronic");
        when(tagRepository.findById(1L)).thenReturn(Optional.of(new TagEntity()));
        assertDoesNotThrow(() -> tagServiceImp.updateById(1L, tagIn));
        assertNotNull(tagIn);
        verify(tagRepository, times(1)).updateById(1L, tagIn);
    }

    @Test
    public void updateById_idIsNotValid_exception() {
        TagIn tagIn = new TagIn();
        tagIn.setName("electronic");
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> tagServiceImp.updateById(1L, tagIn));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }


    private TagEntity createTagEntity() {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName("electronic");
        tagEntity.setId(1L);
        return tagEntity;
    }

    private List<TagEntity> tagEntities(int count) {
        List<TagEntity> tagEntities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            TagEntity entity = new TagEntity();
            entity.setName("electronic" + i);
            tagEntities.add(entity);
        }
        return tagEntities;
    }
}