package com.example.test2.services.imp;

import com.example.test2.errorHandling.exception.CustomException;
import com.example.test2.model.dtos.ProfileIn;
import com.example.test2.model.dtos.UserIn;
import com.example.test2.model.dtos.UserOut;
import com.example.test2.model.entities.ProfileEntity;
import com.example.test2.model.entities.UserEntity;
import com.example.test2.repositories.ProfileRepository;
import com.example.test2.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(UserServiceImp.class)
public class UserServiceImpTest {

    @Spy
    @InjectMocks
    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private ProfileServiceImp profileServiceImp;

    @Test
    public void getAll_success() {
        List<UserEntity> list = this.userEntities(5);
        Page<UserEntity> entityPage = new PageImpl<>(list);
        Pageable pageable = PageRequest.of(0,2);
        when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(entityPage);
        List<UserOut> userOuts = userServiceImp.getAll(pageable);
        assertNotNull(userOuts);
        assertEquals(list.size(), userOuts.size());
    }

    @Test
    public void getAll_dontHaveAnyData_exception() {
        List<UserEntity> list = new ArrayList<>();
        Page<UserEntity> entityPage = new PageImpl<>(list);
        when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(entityPage);
        Pageable pageable = PageRequest.of(0,2);
        CustomException exception = assertThrows(CustomException.class,
                () -> userServiceImp.getAll(pageable));
        assertEquals("you dont have any data", exception.getMessage());
        assertEquals(1004, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());

    }

    @Test
    public void create_success() {
        ProfileIn profile = new ProfileIn();
        profile.setCity("chavosh");
        profile.setCountry("ir");
        profile.setZipCode("123");

        UserIn userIn = this.userIn();
        userIn.setProfileIn(profile);

        when(profileServiceImp.create(Mockito.any(ProfileIn.class))).thenReturn(new ProfileEntity());
        userIn.setProfileIn(profile);
        when(userRepository.save(this.createUserEntity())).thenReturn(new UserEntity());

        UserOut userOut = userServiceImp.create(userIn);
    }

    @Test
    public void deleteById_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        assertDoesNotThrow(() -> userServiceImp.deleteById(1L));
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void deleteById_idIsNotValid_exception() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> userServiceImp.deleteById(1L));
        assertEquals("The ID you entered does not exist", exception.getMessage());
        assertEquals(1001, exception.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void getById_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        userServiceImp.getById(1L);
    }

    @Test
    public void getById_idIsNotValid_exception() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exceptions = assertThrows(CustomException.class,
                () -> userServiceImp.getById(1L));
        assertEquals("The ID you entered does not exist", exceptions.getMessage());
        assertEquals(1001, exceptions.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exceptions.getStatus());
    }

    @Test
    public void update_success() {
        UserIn userIn = this.userIn();
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        profileRepository.updateById(2L, userIn);
        userServiceImp.update(1L, userIn);
    }

    @Test
    public void update_idIsNotValid_exception() {
        UserIn userIn = this.userIn();

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        CustomException exceptions = assertThrows(CustomException.class,
                () -> userServiceImp.update(1L, userIn));
        assertEquals("The ID you entered does not exist", exceptions.getMessage());
        assertEquals(1001, exceptions.getCode());
        assertEquals(HttpStatus.NOT_FOUND, exceptions.getStatus());
    }

    private UserEntity createUserEntity() {
        UserEntity user = new UserEntity();
        user.setId(0L);
        user.setFirstName("ahmad");
        user.setLastName("babak");
        user.setEmail("bob@gmail.com");
        user.setAge(20);
        return user;
    }

    private List<UserEntity> userEntities(int count) {
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            UserEntity userEntity = this.createUserEntity();
            userEntity.setFirstName("shahab" + i);
            list.add(userEntity);
        }
        return list;
    }

    private UserIn userIn() {
        UserIn userIn = new UserIn();
        userIn.setPassword("123");
        userIn.setAge(20);
        userIn.setEmail("bob@gmail.com");
        userIn.setFirstName("ahmad");
        userIn.setLastName("babak");
        return userIn;
    }

}