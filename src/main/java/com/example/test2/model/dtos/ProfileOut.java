package com.example.test2.model.dtos;

import com.example.test2.model.entities.Profile;
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

    public ProfileOut(Profile profile) {
        this.country = profile.getCountry();
        this.city = profile.getCity();
        this.zipCode = profile.getZipCode();
    }
}
