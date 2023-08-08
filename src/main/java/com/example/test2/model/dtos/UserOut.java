package com.example.test2.model.dtos;

import com.example.test2.model.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOut {

    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private ProfileOut profile;


    public UserOut(UserEntity user) {
        if (user != null){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.email = user.getEmail();
        if (Hibernate.isInitialized(user.getProfileEntity())&& user.getProfileEntity() !=null)
        this.profile = new ProfileOut(user.getProfileEntity());
        }
    }
}
