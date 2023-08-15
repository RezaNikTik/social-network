package com.example.test2.model.dtos;

import com.example.test2.model.entities.PostEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Test
    public void convertToEntity_publishDateIsLessThanCurrentTime_Exception(){
        PostIn postIn = new PostIn();
        postIn.setTitle("Test title");
        postIn.setPublishDate(LocalDateTime.parse("2022-04-22T20:30:00"));

        PostEntity post = new PostEntity();

        PostEntity entity = postIn.convertToEntity(post);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PostIn>> violations= validator.validate(postIn);
        assertEquals(1,violations.size());
        ConstraintViolation<PostIn> violation = violations.iterator().next();
        assertEquals("The time you entered is less than the current time",violation.getMessage());
    }


}