package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
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
public class ProfileInTest {

    @Test
    public void convertToEntity_WithNullProfileEntity() {
        ProfileIn profile = this.profileIn();

        ProfileEntity results = profile.convertToEntity(null);

        assertEquals(profile.getCountry(), results.getCountry());
        assertEquals(profile.getCity(), results.getCity());
        assertEquals(profile.getZipCode(), results.getZipCode());
    }

    @Test
    public void convertToEntity_WithProfileEntity() {
        ProfileIn profile = this.profileIn();

        ProfileEntity profileEntity = new ProfileEntity();

        ProfileEntity results = profile.convertToEntity(profileEntity);

        assertEquals(profile.getCountry(), results.getCountry());
        assertEquals(profile.getCity(), results.getCity());
        assertEquals(profile.getZipCode(), results.getZipCode());
    }

    @Test
    public void convertToEntity_WithNullZipCode() {
        ProfileIn profile = this.profileIn();
        profile.setZipCode(null);
        ProfileEntity results = profile.convertToEntity(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ProfileIn>> violations = validator.validate(profile);
        assertEquals(1, violations.size());
        ConstraintViolation<ProfileIn> violation = violations.iterator().next();
        assertEquals("your zipCode most not null", violation.getMessage());
    }

    private ProfileIn profileIn() {
        ProfileIn profile = new ProfileIn();
        profile.setZipCode("123");
        profile.setCountry("USA");
        profile.setCity("DC");
        return profile;
    }
}