package com.example.test2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country",nullable = false,length = 100)
    private String country;

    @Column(name = "city",nullable = false,length = 100)
    private String city;

    @Column(name = "zipCode",nullable = false,length = 100)
    private String zipCode;


    @OneToOne(mappedBy = "profileEntity",cascade = CascadeType.ALL)
    private UserEntity userEntity;



}
