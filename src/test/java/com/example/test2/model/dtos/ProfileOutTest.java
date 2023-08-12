package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfileOutTest {

    @Test
    public void convertToEntity_WithNullProfileEntity() {
        new ProfileOut(null);

    }

    @Test
    public void convertToEntity_WithNonNullProfileEntity() {
        ProfileEntity profile = new ProfileEntity();
        profile.setZipCode("123");
        profile.setCountry("USA");
        profile.setCity("DC");

        ProfileOut profileOuts = new ProfileOut(profile);

        assertEquals(profile.getId(), profileOuts.getProfileId());
        assertEquals(profile.getCountry(), profileOuts.getCountry());
        assertEquals(profile.getCity(), profileOuts.getCity());
        assertEquals(profile.getZipCode(), profileOuts.getZipCode());
    }

}