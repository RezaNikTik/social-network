package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import com.example.test2.model.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserOutTest {

    @Test
    public void constructor_WithNullUserEntity() {
        UserOut user = new UserOut(null);
    }

    @Test
    public void constructor_WithNonNullUserEntity() {
        UserOut userOut = new UserOut(this.userEntity());
        assertEquals(this.userEntity().getAge(), userOut.getAge());
        assertEquals(this.userEntity().getFirstName(), userOut.getFirstName());
        assertEquals(this.userEntity().getLastName(), userOut.getLastName());
    }

    @Test
    public void constructor_WithNonNullUserEntityAndRelation() {
        UserOut userOut = new UserOut(this.userEntity());

        assertEquals(this.userEntity().getAge(), userOut.getAge());
        assertEquals(this.userEntity().getFirstName(), userOut.getFirstName());
        assertEquals(this.userEntity().getLastName(), userOut.getLastName());
        assertEquals(this.userEntity().getProfileEntity().getCity(), userOut.getProfile().getCity());
        assertEquals(this.userEntity().getProfileEntity().getCountry(), userOut.getProfile().getCountry());
    }


    private UserEntity userEntity() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("re");
        user.setLastName("bo");
        user.setAge(26);
        user.setEmail("sdfjm");
        user.setPassword("dfjkjh");
        user.setProfileEntity(this.profileEntity());
        return user;
    }

    private ProfileEntity profileEntity() {
        ProfileEntity profile = new ProfileEntity();
        profile.setCity("DC");
        profile.setCountry("USA");
        profile.setZipCode("123");
        return profile;
    }
}