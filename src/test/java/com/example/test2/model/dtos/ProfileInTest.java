package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfileInTest {

    @Test
    public void convertToProfile_WithData_success() {
        ProfileIn profile = new ProfileIn();

        profile.setZipCode("123");
        profile.setCountry("USA");
        profile.setCity("DC");

        ProfileEntity profileEntity = new ProfileEntity();

        ProfileEntity results = profile.convertToProfile(profileEntity);

        assertEquals(profile.getCountry(), results.getCountry());
        assertEquals(profile.getCity(), results.getCity());
        assertEquals(profile.getZipCode(), results.getZipCode());
    }

    @Test
    public void convertToProfile_WithNullProfileEntity_success() {
        ProfileIn profile = new ProfileIn();

        profile.setZipCode("123");
        profile.setCountry("USA");
        profile.setCity("DC");

        ProfileEntity results = profile.convertToProfile(null);

        assertEquals(profile.getCountry(), results.getCountry());
        assertEquals(profile.getCity(), results.getCity());
        assertEquals(profile.getZipCode(), results.getZipCode());
    }

    @Test
    public void convertToProfile_WithNullZipCode_success() {
        ProfileIn profile = new ProfileIn();

        profile.setZipCode(null);
        profile.setCountry("USA");
        profile.setCity("DC");

        ProfileEntity results = profile.convertToProfile(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ProfileIn>> violations = validator.validate(profile);
        assertEquals(1, violations.size());
        ConstraintViolation<ProfileIn> violation = violations.iterator().next();
        assertEquals("your zipCode most not null", violation.getMessage());
    }
}