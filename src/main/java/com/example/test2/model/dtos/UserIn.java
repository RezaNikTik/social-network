package com.example.test2.model.dtos;

import com.example.test2.model.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIn {
    private String firstName;
    private String lastName;
    private String password;
    private Integer age;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}$", message = "your email is not valid")
    private String email;
    private ProfileIn profileIn;


    public UserEntity convertToEntity(UserEntity user) {
        if (user == null) {
            user = new UserEntity();
        }
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setPassword(this.getPassword());
        user.setAge(this.getAge());
        user.setEmail(this.getEmail());
        return user;
    }
}
