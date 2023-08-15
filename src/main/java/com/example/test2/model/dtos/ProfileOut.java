package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileOut  implements Serializable {

    private Long profileId;
    private String country;
    private String city;
    private String zipCode;

    public ProfileOut(ProfileEntity profileEntity) {
        if (profileEntity != null) {
            this.profileId = profileEntity.getId();
            this.country = profileEntity.getCountry();
            this.city = profileEntity.getCity();
            this.zipCode = profileEntity.getZipCode();
        }
    }
}
