package com.example.test2.model.dtos;

import com.example.test2.model.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInTest {


    @Test
    public void convertToEntity_WithNullUserIn() {
        UserIn userIn = this.userIn();

        UserEntity user = userIn.convertToEntity(null);

        assertEquals(userIn.getFirstName(), user.getFirstName());
        assertEquals(userIn.getLastName(), user.getLastName());
        assertEquals(userIn.getAge(), user.getAge());
        assertEquals(userIn.getEmail(), user.getEmail());
        assertEquals(userIn.getPassword(), user.getPassword());
    }

    @Test
    public void convertToEntity_WithUserIn() {
        UserIn userIn = this.userIn();

        UserEntity userEntity = new UserEntity();

        UserEntity user = userIn.convertToEntity(userEntity);

        assertEquals(userIn.getFirstName(), user.getFirstName());
        assertEquals(userIn.getLastName(), user.getLastName());
        assertEquals(userIn.getAge(), user.getAge());
        assertEquals(userIn.getEmail(), user.getEmail());
        assertEquals(userIn.getPassword(), user.getPassword());
    }

    @Test
    public void convertToEntity_WithNullUserInAndNotValidEmail() {
        UserIn userIn = this.userIn();

        UserEntity user = userIn.convertToEntity(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserIn>> violations = validator.validate(userIn);
        assertEquals(1, violations.size());
        ConstraintViolation<UserIn> violation = violations.iterator().next();
        assertEquals("your email is not valid", violation.getMessage());
    }

    private UserIn userIn() {
        UserIn userIn = new UserIn();
        userIn.setFirstName("reza");
        userIn.setLastName("pak");
        userIn.setPassword("123");
        userIn.setAge(1);
        userIn.setEmail("sdlfjl;jd");
        return userIn;
    }
}