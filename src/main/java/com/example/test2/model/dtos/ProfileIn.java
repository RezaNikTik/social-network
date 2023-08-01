package com.example.test2.model.dtos;

import com.example.test2.model.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileIn {

    private String country;
    private String city;
    @NotNull(message = "your zipCode most not null")
    private String zipCode;


    public Profile convertToProfile(Profile profile) {
        if (profile == null){
            new Profile();
        }
        profile.setCity(this.getCity());
        profile.setCountry(this.getCountry());
        profile.setZipCode(this.getZipCode());
        return profile;
    }
}
