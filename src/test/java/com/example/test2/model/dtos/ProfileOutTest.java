package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfileOutTest {


    @Mock
    private ProfileEntity mockProfileEntity;

    @Test
    public void testConstructorWithNonNullProfileEntity() {
        when(mockProfileEntity.getId()).thenReturn(1L);
        when(mockProfileEntity.getCountry()).thenReturn("Country");
        when(mockProfileEntity.getCity()).thenReturn("City");
        when(mockProfileEntity.getZipCode()).thenReturn("12345");

        ProfileOut profileOut = new ProfileOut(mockProfileEntity);

        assertEquals(1L, profileOut.getProfileId());
        assertEquals("Country", profileOut.getCountry());
        assertEquals("City", profileOut.getCity());
        assertEquals("12345", profileOut.getZipCode());


    }

    @Test
    public void testConstructorWithNullProfileEntity() {
        ProfileOut profileOut = new ProfileOut(null);

        assertEquals(null, profileOut.getProfileId());
        assertEquals(null, profileOut.getCountry());
        assertEquals(null, profileOut.getCity());
        assertEquals(null, profileOut.getZipCode());
    }
}