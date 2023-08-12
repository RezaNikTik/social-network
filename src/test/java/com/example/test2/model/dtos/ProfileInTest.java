package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfileInTest {


    private ProfileIn profileIn;

    @Mock
    private ProfileEntity mockProfileEntity;

    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        profileIn = new ProfileIn();
        profileIn.setCountry("Country");
        profileIn.setCity("City");
        profileIn.setZipCode("12345");
    }

    @Test
    public void testConvertToProfile_NewProfileEntity() {
        ProfileEntity result = profileIn.convertToProfile(null);

        assertEquals("Country", result.getCountry());
        assertEquals("City", result.getCity());
        assertEquals("12345", result.getZipCode());
    }

    @Test
    public void testConvertToProfile_ExistingProfileEntity() {
        when(mockProfileEntity.getCountry()).thenReturn("OldCountry");
        when(mockProfileEntity.getCity()).thenReturn("OldCity");
        when(mockProfileEntity.getZipCode()).thenReturn("54321");

        ProfileEntity result = profileIn.convertToProfile(mockProfileEntity);

        assertEquals("Country", result.getCountry());
        assertEquals("City", result.getCity());
        assertEquals("12345", result.getZipCode());

        verify(mockProfileEntity, times(1)).setCountry("Country");
        verify(mockProfileEntity, times(1)).setCity("City");
        verify(mockProfileEntity, times(1)).setZipCode("12345");
    }

    @Test
    public void testConvertToProfile_Validation() {
        profileIn.setZipCode(null);

        ProfileEntity result = profileIn.convertToProfile(null);

        assertEquals("Country", result.getCountry());
        assertEquals("City", result.getCity());
        assertEquals(null, result.getZipCode());
    }

}