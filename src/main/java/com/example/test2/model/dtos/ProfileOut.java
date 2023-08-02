package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileOut {

    private String country;
    private String city;
    private String zipCode;

    public ProfileOut(ProfileEntity profileEntity) {
        this.country = profileEntity.getCountry();
        this.city = profileEntity.getCity();
        this.zipCode = profileEntity.getZipCode();
    }
}
