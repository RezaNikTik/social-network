package com.example.test2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity(name = "UserEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "FirstName", nullable = false, length = 200)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 200)
    private String lastName;

    @Column(name = "Password", nullable = false, length = 200)
    private String password;

    @Column(name = "Age", nullable = false, length = 2)
    private Integer age;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToOne(mappedBy = "userEntity")
    @PrimaryKeyJoinColumn
    private ProfileEntity profileEntity;
}
