package com.example.test2.model.dtos;
import com.example.test2.model.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOut {

    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
//    private Long profileId;
//    private String country;
//    private String city;
//    private String zipCode;
    ProfileOut profile;
    private Long profileId;


    public  UserOut (UserEntity user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
//        this.profileId = user.getProfileEntity().getId();
//        this.country=user.getProfileEntity().getCountry();
//        this.city= user.getProfileEntity().getCity();
//        this.zipCode=user.getProfileEntity().getZipCode();
        this.profile = new ProfileOut(user.getProfileEntity());
        this.profileId=user.getProfileEntity().getId();
    }
}
