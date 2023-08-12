package com.example.test2.model.dtos;

import com.example.test2.model.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserOutTest {


    @Mock
    private UserEntity userEntity;

    @Test
    public void constructor_WithNonNullUserEntity() {
        Mockito.when(userEntity.getAge()).thenReturn(1);
        Mockito.when(userEntity.getFirstName()).thenReturn("re");
        Mockito.when(userEntity.getLastName()).thenReturn("bo");
        Mockito.when(userEntity.getEmail()).thenReturn("sdfjm");
        Mockito.when(userEntity.getPassword()).thenReturn("123");

        UserOut userOut = new UserOut(userEntity);
        assertEquals(userEntity.getAge(), userOut.getAge());
        assertEquals(userEntity.getFirstName(), userOut.getFirstName());
        assertEquals(userEntity.getLastName(), userOut.getLastName());
    }

    @Test
    public void constructor_WithNullUserEntity() {
        UserOut user = new UserOut(null);
        assertEquals(null, user.getLastName());
        assertEquals(null, user.getFirstName());
        assertEquals(null, user.getEmail());
    }
}