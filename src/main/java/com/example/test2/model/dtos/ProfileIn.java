package com.example.test2.model.dtos;

import com.example.test2.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileIn {

    private String country;
    private String city;
    @NotNull(message = "your zipCode most not null")
    private String zipCode;


    public ProfileEntity convertToProfile(ProfileEntity profileEntity) {
        if (profileEntity == null){
            profileEntity=new ProfileEntity();
        }
        profileEntity.setCity(this.getCity());
        profileEntity.setCountry(this.getCountry());
        profileEntity.setZipCode(this.getZipCode());
        return profileEntity;
    }
}
